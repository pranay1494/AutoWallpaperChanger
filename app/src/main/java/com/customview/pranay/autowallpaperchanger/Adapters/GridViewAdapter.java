package com.customview.pranay.autowallpaperchanger.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private SelectNewWallpaper selectNewWallpaper;

    private static final int ADD_lOCAL_WALLPAPER = 1;
    private static final int WALLPAPER = 2;

    public GridViewAdapter(MainActivity mainActivity, SelectNewWallpaper selectNewWallpaper) {
        this.context = mainActivity;
        this.selectNewWallpaper = selectNewWallpaper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ADD_lOCAL_WALLPAPER: {
                return new AddNewLocalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addnewiamge_layout, null));
            }
            case WALLPAPER: {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout, null));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ADD_lOCAL_WALLPAPER: {
                AddNewLocalViewHolder addNewLocalViewHolder = (AddNewLocalViewHolder) holder;
                addNewLocalViewHolder.addNewcardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectNewWallpaper.addNewWallpaperClicked();
                    }
                });
                break;
            }
            case WALLPAPER: {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.wallpaperThumb.setImageBitmap(getScaledBitmap(ChangeWallpaperModel.getInstance().getImages().get(position), 150, 150));
                break;
            }
        }
    }

    private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, sizeOptions);

        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
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
        CardView cardView;
        ImageView wallpaperThumb;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            wallpaperThumb = (ImageView) itemView.findViewById(R.id.wallpaperImage);
        }
    }

    public class AddNewLocalViewHolder extends RecyclerView.ViewHolder {
        CardView addNewcardView;
        ImageView addNewImage;

        public AddNewLocalViewHolder(View itemView) {
            super(itemView);
            addNewcardView = (CardView) itemView.findViewById(R.id.cardView_addimage);
            addNewImage = (ImageView) itemView.findViewById(R.id.addnewImage);
        }
    }

    public interface SelectNewWallpaper {
        void addNewWallpaperClicked();
    }
}
