package com.customview.pranay.autowallpaperchanger.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Pranay on 27-11-2016.
 */

public class UpperTriangle extends View {
    public UpperTriangle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpperTriangle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UpperTriangle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        Path path = new Path();
        path.moveTo(width,0);
        path.lineTo(width-width/3,0);
        path.lineTo(width,width/3);
        path.lineTo(width,0);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawPath(path,paint);
        invalidate();
    }
}
