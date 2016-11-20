package com.customview.pranay.autowallpaperchanger.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.customview.pranay.autowallpaperchanger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranay on 20-11-2016.
 */

public class ChangeWallpaperModel {

    private List<Bitmap> images = new ArrayList<>();
    public static ChangeWallpaperModel changeWallpaperModel;

    private ChangeWallpaperModel() {
    }

    public static ChangeWallpaperModel getInstance(){
        if(changeWallpaperModel!=null){
            return changeWallpaperModel;
        }
        else{
            return new ChangeWallpaperModel();
        }
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public void clearList(){
        images.clear();
    }

    public void addToList(Bitmap id){
        images.add(id);
    }

    public void removeFromLList(int position){
        if(!images.isEmpty()&&images.size()>1){
            images.remove(position);
        }
    }
}
