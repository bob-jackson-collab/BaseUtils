package com.ys.baseproject.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.mapapi.map.Overlay;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.ys.baseproject.net.NetChangeObserver;
import com.ys.baseproject.net.NetStateReceiver;
import com.ys.baseproject.utils.AppManager;
import com.ys.baseproject.utils.NetUtils;
import com.ys.baseproject.view.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/10.
 * yangsong
 */

public abstract class BaseActivity extends AppCompatActivity {

    private NetChangeObserver mObserver;

    public  BaseActivity mContext;


    public abstract void handleLayoutAndData();

    public abstract int getContentViewId();

    public void onNetConnected(NetUtils.NetType type){}

    public void onNetDisConnect(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setBaseConfig();
        //监听网络的广播
        mObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                onNetConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                 onNetDisConnect();
            }
        };
        //注册监听
        NetStateReceiver.registerObserver(mObserver);
        //设置Activity的布局
        setContentView(getContentViewId());

        //判断从上个Activity传过来是否有值
        handleLayoutAndData();
    }

    private void setBaseConfig(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //注解butterknife
        ButterKnife.bind(this);
        //加入activity管理容器
        AppManager.getAppManager().addActivity(this);
        //设置状态栏的颜色 默认是红色
        setStatusBarColor(Color.RED);
        //设置屏幕只能竖直
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 做一些判断Token是否失效的操作
         */
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除监听
        NetStateReceiver.removeRegisterObserver(mObserver);
        //移除容器的activity
        AppManager.getAppManager().finishActivity(this);
//        ButterKnife.unbind(this);

    }

    public void setStatusBarColor(int color){
        //如果Api版本大于19的
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setNavigationBarTintEnabled(false);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintColor(color);
        }
    }


    public void gotoActivity(Class<?> clz){
        gotoActivity(clz,false,null);
    }

    public void gotoActivity(Class<?> clz,boolean isCurrentActivity){
        gotoActivity(clz,isCurrentActivity,null);
    }

    //activity之间的跳转 默认不关闭当前Activity
    public void gotoActivity(Class<?> clz,boolean isCurrentActivity,Bundle ex){
        Intent intent = new Intent(this,clz);
        if(ex!=null){
            intent.putExtras(ex);
        }
        startActivity(intent);
        if(isCurrentActivity){
            finish();
        }
    }

    public void showLoaing() {
        LoadingDialog.showLoading(this);
    }

    public void showLoaing(String msg) {
        LoadingDialog.showLoading(this, msg, true);
    }

    public void dismissLoading() {
        LoadingDialog.disDialog();
    }
}
