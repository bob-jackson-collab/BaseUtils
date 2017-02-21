package com.ys.baseproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/11.
 */

public class PreferenceManager {

    private static PreferenceManager mPreferenceManager;

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferences.Editor mEditor;

    private static final String spName = "app_sp";


    public static void init(Context context) {
        if (context == null) {
            return;
        }

        mSharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }

    public static PreferenceManager getInstance() {

        synchronized (PreferenceManager.class) {
            if (mPreferenceManager == null) {
                mPreferenceManager = new PreferenceManager();
            }
            return mPreferenceManager;
        }
    }

    public static void saveUserName(Context context,String userName){
        if(context==null){
            return;
        }
        mEditor.putString(userName,userName);
        mEditor.apply();
    }

    public static String getUserName(Context context,String userName){

        if(context==null){
            return null;
        }

        return  mSharedPreferences.getString(userName,"");
    }

    public static void clearUserName(String userName,Context context){

        if(context==null){
            return;
        }
        mEditor.remove(userName);
        mEditor.commit();
    }

}
