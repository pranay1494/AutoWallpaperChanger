package com.customview.pranay.autowallpaperchanger.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.customview.pranay.autowallpaperchanger.ChanegeWallpaperService;
import com.customview.pranay.autowallpaperchanger.MainActivity;
import com.customview.pranay.autowallpaperchanger.Model.ChangeWallpaperModel;
import com.customview.pranay.autowallpaperchanger.R;

/**
 * Created by Pranay on 20-11-2016.
 */

public class GridViewAdapter extends RecyclerView.Adapter {

    private Context context;

    private static final int ADD_lOCAL_WALLPAPER = 1;
    private static final int WALLPAPER = 2;

    public GridViewAdapter(MainActivity mainActivity) {
        this.context = mainActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ADD_lOCAL_WALLPAPER:{
                return new AddNewLocalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addnewiamge_layout,null));
            }
            case WALLPAPER:{
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout,null));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ChangeWallpaperModel.getInstance().getImages().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ADD_lOCAL_WALLPAPER : WALLPAPER;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView wallpaperThumb;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            wallpaperThumb = (ImageView) itemView.findViewById(R.id.wallpaperImage);
        }
    }
    public class AddNewLocalViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView addNewImage;
        public AddNewLocalViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView_addimage);
            addNewImage = (ImageView) itemView.findViewById(R.id.addnewImage);
        }
    }
}
