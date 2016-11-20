package com.customview.pranay.autowallpaperchanger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.customview.pranay.autowallpaperchanger.Adapters.GridViewAdapter;
import com.customview.pranay.autowallpaperchanger.Model.ChangeWallpaperModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recWallpaper);

        startService(new Intent(this,ChanegeWallpaperService.class));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.addnewimage);
        ChangeWallpaperModel.getInstance().addToList(bitmap);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        GridViewAdapter gridViewAdapter = new GridViewAdapter(this);
        recyclerView.setAdapter(gridViewAdapter);
    }
}
