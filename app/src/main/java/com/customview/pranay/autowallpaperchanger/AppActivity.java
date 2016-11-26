package com.customview.pranay.autowallpaperchanger;

import android.app.Application;
import android.content.SharedPreferences;

import com.customview.pranay.autowallpaperchanger.Model.ChangeWallpaperModel;
import com.google.gson.Gson;

/**
 * Created by Pranay on 26-11-2016.
 */

public class AppActivity extends Application{

    ChangeWallpaperModel changeWallpaperModel;
    @Override
    public void onCreate() {
        super.onCreate();
        changeWallpaperModel = ChangeWallpaperModel.getInstance();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MODAL_PREF", MODE_PRIVATE);
        String jsonString = pref.getString("MODAL_STRING",null);
        changeWallpaperModel = new Gson().fromJson(jsonString,ChangeWallpaperModel.class);
        ChangeWallpaperModel.getInstance().setInstance(changeWallpaperModel);
    }
}
