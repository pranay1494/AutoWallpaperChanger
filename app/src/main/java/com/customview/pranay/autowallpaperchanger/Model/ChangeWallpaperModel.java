package com.customview.pranay.autowallpaperchanger.Model;

import com.customview.pranay.autowallpaperchanger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranay on 20-11-2016.
 */

public class ChangeWallpaperModel {

    private List<String> images = new ArrayList<>();
    public static ChangeWallpaperModel changeWallpaperModel;

    private ChangeWallpaperModel() {
        images.add("addimage");

    }

    public static ChangeWallpaperModel getInstance(){
        if(changeWallpaperModel!=null){
            return changeWallpaperModel;
        }
        else{
            return new ChangeWallpaperModel();
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void clearList(){
        images.clear();
    }

    public void addToList(String id){
        images.add(id);
    }

    public void removeFromList(int position){
        if(!images.isEmpty()&&images.size()>1){
            images.remove(position);
        }
    }
}
