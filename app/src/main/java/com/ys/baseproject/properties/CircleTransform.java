package com.ys.baseproject.properties;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by yunshan on 17/3/30.
 */

public class CircleTransform extends BitmapTransformation{

    public CircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return getCircleBitmap(pool,toTransform);
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    public Bitmap getCircleBitmap(BitmapPool pool,Bitmap toTransform){

        int size = Math.min(toTransform.getWidth(),toTransform.getHeight());

        int startX = (toTransform.getWidth() - size) / 2;
        int startY = (toTransform.getHeight() - size) /2;

        Bitmap squared = Bitmap.createBitmap(toTransform,startX,startY,size,size);

        Bitmap result = pool.get(size,size, Bitmap.Config.ARGB_8888);

        if(result == null){
            result = Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_8888);
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        if (startX != 0 || startY != 0) {
            // source isn't square, move viewport to center
            Matrix matrix = new Matrix();
            matrix.setTranslate(-startX, -startY);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);

        Canvas canvas = new Canvas(result);
        canvas.drawCircle(size/2,size/2,size,paint);
        return result;

    }


    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

}
