package com.ys.baseproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by yunshan on 17/5/11.
 */

public class PassWordView extends EditText {

    private int mWidth;
    private int mHeight;
    private RectF rectF;
    private int maxCount = 6;
    private int startX;
    private int startY;
    private int dividedWStart;
    private int length;
    private Paint mPaint;
    private PasswordListener passwordListener;
    private String compareWord;

    public PassWordView(Context context) {
        this(context, null);
    }

    public PassWordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassWordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setFocusable(true);
        this.setCursorVisible(false);
        rectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setCompareWord(String compareWord,PasswordListener passwordListener){
        this.compareWord = compareWord;
        this.passwordListener = passwordListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mWidth = MeasureSpec.getSize(widthMeasureSpec);
//        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        dividedWStart = w / maxCount;

        startX = w / maxCount / 2;
        startY = h / 2;
        rectF.set(0, 0, mWidth, mHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        canvas.drawRoundRect(rectF, 20,20, mPaint);
        for (int i = 0; i < maxCount - 1; i++) {
            canvas.drawLine(dividedWStart * (i + 1), 0, dividedWStart * (i + 1), mHeight, mPaint);
        }

        drawPointCircle(canvas);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        length = text.length();

        if(passwordListener != null && length == maxCount){
            if(TextUtils.equals(compareWord,text)){
                passwordListener.equals();
            }else{
                passwordListener.difference();
            }
        }
        invalidate();
    }

    //画密码
    private void drawPointCircle(Canvas canvas) {
        Paint point = new Paint();
        point.setStyle(Paint.Style.FILL);
        for (int i = 0; i < length; i++) {

            canvas.drawCircle(mWidth / maxCount / 2 + (i) * mWidth / maxCount, mHeight / 2, 15, point);
        }
    }

    public interface PasswordListener{

         void equals();

         void difference();
    }
}
