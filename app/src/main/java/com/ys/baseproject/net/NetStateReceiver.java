package com.ys.baseproject.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.ys.baseproject.utils.NetUtils;

import java.util.ArrayList;

/* 使用广播去监听网络

        * Created by deng on 16/9/13.

        */

public class NetStateReceiver extends BroadcastReceiver {


    public final static String CUSTOM_ANDROID_NET_CHANGE_ACTION = "com.zhanyun.api.netstatus.CONNECTIVITY_CHANGE";

    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private final static String TAG = NetStateReceiver.class.getSimpleName();

    private static boolean isNetAvailable = false;  //默认无网络

    private static NetUtils.NetType mNetType;   //网络类型

    private static ArrayList<NetChangeObserver> mNetChangeObservers = new ArrayList<NetChangeObserver>(); //网络观察者

    private static BroadcastReceiver mBroadcastReceiver;


    //单利创建实例
    private static BroadcastReceiver getReceiver() {

        if (null == mBroadcastReceiver) {

            synchronized (NetStateReceiver.class) {

                if (null == mBroadcastReceiver) {

                    mBroadcastReceiver = new NetStateReceiver();

                }

            }

        }

        return mBroadcastReceiver;

    }


    @Override

    public void onReceive(Context context, Intent intent) {

        mBroadcastReceiver = NetStateReceiver.this;

        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION) || intent.getAction().equalsIgnoreCase(CUSTOM_ANDROID_NET_CHANGE_ACTION)) {

            if (!NetUtils.isNetworkAvailable(context)) {  // 如果无网络

//                LogHelper.e(this.getClass(), "<--- network disconnected --->");

                isNetAvailable = false;

            } else {

//                LogHelper.e(this.getClass(), "<--- network connected --->");

                isNetAvailable = true;

                //获取网络类型
                mNetType = NetUtils.getAPNType(context);

            }
            //通知观察者
            notifyObserver();

        }

    }


    /**
     * 注册
     *
     * @param mContext
     */

    public static void registerNetworkStateReceiver(Context mContext) {

        IntentFilter filter = new IntentFilter();

        filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);

        filter.addAction(ANDROID_NET_CHANGE_ACTION);

        mContext.registerReceiver(getReceiver(), filter);

    }


    /**
     * 清除
     *
     * @param mContext
     */

    public static void checkNetworkState(Context mContext) {

        Intent intent = new Intent();

        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);

        mContext.sendBroadcast(intent);

    }


    /**
     * 反注册
     *
     * @param mContext
     */

    public static void unRegisterNetworkStateReceiver(Context mContext) {

        if (mBroadcastReceiver != null) {

            try {

                mContext.unregisterReceiver(mBroadcastReceiver);

            } catch (Exception e) {


            }

        }


    }


    public static boolean isNetworkAvailable() {

        return isNetAvailable;

    }


    public static NetUtils.NetType getAPNType() {

        return mNetType;

    }

    // 观察者做相应的action
    private void notifyObserver() {

        if (!mNetChangeObservers.isEmpty()) {

            int size = mNetChangeObservers.size();

            for (int i = 0; i < size; i++) {

                NetChangeObserver observer = mNetChangeObservers.get(i);

                if (observer != null) {

                    if (isNetworkAvailable()) {

                        observer.onNetConnected(mNetType);

                    } else {

                        observer.onNetDisConnect();

                    }

                }

            }

        }

    }


    /**
     * 添加网络监听
     *
     * @param observer
     */

    public static void registerObserver(NetChangeObserver observer) {

        if (mNetChangeObservers == null) {

            mNetChangeObservers = new ArrayList<NetChangeObserver>();
        }
        Log.e("--->>","添加网络");
        mNetChangeObservers.add(observer);

    }


    /**
     * 移除网络监听
     *
     * @param observer
     */

    public static void removeRegisterObserver(NetChangeObserver observer) {

        if (mNetChangeObservers != null) {

            if (mNetChangeObservers.contains(observer)) {

                Log.e("--->","移除网络");

                mNetChangeObservers.remove(observer);

            }

        }

    }
}
