package com.ys.baseproject.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by yunshan on 17/3/29.
 */

public class CircleImageDrawable extends Drawable{

    private RectF rectF;

    private Paint mPaint;

    private Bitmap bitmap;

    private int mWidth;

    public CircleImageDrawable(Bitmap bitmap){

        this.bitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint.setAntiAlias(true);

        mPaint.setShader(bitmapShader);

        mWidth = Math.min(bitmap.getWidth(),bitmap.getHeight());

    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left,top,right,bottom);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return mWidth;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
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
