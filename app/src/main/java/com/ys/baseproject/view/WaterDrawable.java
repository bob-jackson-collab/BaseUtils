package com.ys.baseproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by yunshan on 17/3/27.
 */

public class WaterDrawable extends Drawable {

    private int phoneWidth , phoneHeight;
    private Paint mPaint;

    private String text = "hello";

    public WaterDrawable(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        phoneHeight = displayMetrics.heightPixels;
        phoneWidth = displayMetrics.widthPixels;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(15);
    }
    @Override
    public void draw(@NonNull Canvas canvas) {

        mPaint.measureText(text);
        canvas.save();
        canvas.rotate(15);
        Rect bounds = getBounds();

        int width = 100;

        for(int i = 1;i< bounds.width() /width;i++){
            canvas.drawText(text,i*width+20,0,mPaint);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
