package com.customview.pranay.autowallpaperchanger;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.customview.pranay.autowallpaperchanger.Adapters.GridViewAdapter;
import com.customview.pranay.autowallpaperchanger.Model.ChangeWallpaperModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridViewAdapter.SelectNewWallpaper {

    private static final int RESULT_LOAD_IMAGE = 999;
    private RecyclerView recyclerView;
    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recWallpaper);

        startService(new Intent(this,ChanegeWallpaperService.class));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        gridViewAdapter = new GridViewAdapter(this,this);
        recyclerView.setAdapter(gridViewAdapter);
    }

    @Override
    public void addNewWallpaperClicked() {
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            ChangeWallpaperModel.getInstance().addToList(picturePath);
            cursor.close();
            gridViewAdapter.notifyDataSetChanged();
        }
    }
}
