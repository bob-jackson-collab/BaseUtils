package com.ys.baseproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yunshan on 17/5/3.
 */

public class RedPointView extends View {

    private Paint mPaint;
    private int mWidth = 50;
    private int mHeight = 50;

    public RedPointView(Context context) {
        this(context,null);
    }

    public RedPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RedPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
//        if(widthMode == MeasureSpec.EXACTLY){
//
//        }
        setMeasuredDimension(mWidth,mHeight);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2,mWidth /2 ,mWidth /2 ,mPaint);
    }


}
