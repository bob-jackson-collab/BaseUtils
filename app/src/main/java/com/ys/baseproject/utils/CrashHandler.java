package com.ys.baseproject.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import com.ys.baseproject.base.BaseActivity;

/**
 * Created by Administrator on 2017/2/6.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private static final String TAG = "CrashHandler";

    // CrashHandler 实例
    private static CrashHandler INSTANCE = new CrashHandler();

    // 程序的 Context 对象
    private Context mContext;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 保证只有一个 CrashHandler 实例
     */
    private CrashHandler() {
    }

    /**
     * 获取 CrashHandler 实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;

        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        Log.d("TEST", "Crash:init");
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
//            mDefaultHandler.uncaughtException(t, e);
            //用户自己处理
            handleException(e);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                Log.e(TAG, "error : ", interruptedException);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 异常的自定义处理
        // 日志书写之类的。。
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                // 异常的自定义处理
//                MyApplication.getInstance().initData();//对于App进行初始化（因为一般我们都会在Application中进行初始化数据，假如仅仅是跳转到App最开始的页面，这样可能会导致报Null错误）
                Intent intent = new Intent(mContext, BaseActivity.class);//跳转到App最开始的页面
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                Looper.loop();
            }
        }.start();
        return true;
    }


}
