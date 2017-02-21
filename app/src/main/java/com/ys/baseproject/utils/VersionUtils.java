package com.ys.baseproject.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2017/1/10.
 */

public class VersionUtils {

    /**
     * 获取软件版本信息
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        int versionCode = 0;
        try {
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,0);
            versionCode = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(versionCode);
    }
}
