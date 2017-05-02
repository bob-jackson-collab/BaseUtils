package com.ys.baseproject;

import android.app.Application;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.Utils;
import com.uuch.adlibrary.LApplication;
import com.ys.baseproject.utils.UniversalUtils;

/**
 * Created by yunshan on 17/3/28.
 */

public class MainApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(getApplicationContext());

    }
}
