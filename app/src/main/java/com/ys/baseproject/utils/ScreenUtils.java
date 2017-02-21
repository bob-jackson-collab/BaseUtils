package com.ys.baseproject.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ScreenUtils {

    private static DisplayMetrics metrics = new DisplayMetrics();

    private static WindowManager mWindowManager;

    private static ScreenUtils mScreenUtils;

    public static ScreenUtils getInstance(Context context){

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        mWindowManager.getDefaultDisplay().getMetrics(metrics);

        synchronized (ScreenUtils.class) {
            if (mScreenUtils == null) {
                mScreenUtils = new ScreenUtils();
            }
        }

        return mScreenUtils;
    }

    //屏幕
    public static int getScreenWidth(){
        return metrics.widthPixels;
    }

    public static int getScrrentHeight(){
        return metrics.heightPixels;
    }
}
