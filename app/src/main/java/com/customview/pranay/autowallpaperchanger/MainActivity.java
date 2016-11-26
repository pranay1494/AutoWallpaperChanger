package com.customview.pranay.autowallpaperchanger;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.customview.pranay.autowallpaperchanger.Adapters.GridViewAdapter;
import com.customview.pranay.autowallpaperchanger.Model.ChangeWallpaperModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridViewAdapter.SelectNewWallpaper {

    private static final int RESULT_LOAD_IMAGE = 999;
    private RecyclerView recyclerView;
    private GridViewAdapter gridViewAdapter;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recWallpaper);

        startService(new Intent(this,ChanegeWallpaperService.class));

        pref = getApplicationContext().getSharedPreferences("MODAL_PREF", MODE_PRIVATE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        gridViewAdapter = new GridViewAdapter(this,this);
        recyclerView.setAdapter(gridViewAdapter);
    }

    @Override
    public void addNewWallpaperClicked() {
        insertPermissioonToReadWrite();
        /*Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);*/
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
            cursor.close();
            if(picturePath!=null) {
                ChangeWallpaperModel.getInstance().addToList(picturePath);
                gridViewAdapter.notifyDataSetChanged();
                putDatatoSharedPrefs();
            }else {
                Toast.makeText(this, "pick some other image this image cannot be selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void putDatatoSharedPrefs() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MODAL_STRING",new Gson().toJson(ChangeWallpaperModel.getInstance()));
        editor.commit();
    }

    private void insertPermissioonToReadWrite() {
        int hasWritePermission = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        else{
            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                } else {
                    Toast.makeText(MainActivity.this, "Storage permission denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
