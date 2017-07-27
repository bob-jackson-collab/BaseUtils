package com.ys.baseproject.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by yunshan on 17/7/13.
 */

public class DragLayout extends FrameLayout {

    private ViewDragHelper mDragHelper;

    private ViewDragHelper.Callback mCallback;

    private ViewGroup mLeft, mMainContent;

    private int mWidth, mHeight, mRange;


    public DragLayout(@NonNull Context context) {
        this(context, null);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mCallback = new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {  //返回true 代表可以拖拽

                return child == mMainContent;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                if (child == mMainContent) {
                    left = fixLeft(left);
                }
                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //手指抬起后缓慢移动到指定位置
                if (mMainContent.getLeft() < 400) {
                    //关闭菜单
                    //相当于Scroller的startScroll方法
                    mDragHelper.smoothSlideViewTo(mMainContent, 0, 0);
                    ViewCompat.postInvalidateOnAnimation(DragLayout.this);
                } else {
                    //打开菜单
                    mDragHelper.smoothSlideViewTo(mMainContent, mRange, 0);
                    ViewCompat.postInvalidateOnAnimation(DragLayout.this);
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                //dx代表距离上一个滑动时间间隔后的滑动距离
//                if (dx > 0) {//正
//                    mDragOrientation = LEFT_TO_RIGHT;//从左往右
//                } else if (dx < 0) {//负
//                    mDragOrientation = RIGHT_TO_LEFT;//从右往左
//                }
            }
        };
        mDragHelper = ViewDragHelper.create(this, 1.0f, mCallback);
    }

    private int fixLeft(int left) {
        if (left < 0) {
            return 0;
        }
        if (left > mRange) {
            return mRange;
        }
        return left;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mLeft = (ViewGroup) this.getChildAt(0);

        mMainContent = (ViewGroup) this.getChildAt(1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRange = (int) (mWidth * 0.7);
    }
}
