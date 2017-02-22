package com.ys.baseproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.ys.baseproject.utils.PermissionsUtil;

import java.io.File;
import java.io.IOException;
import java.security.Permission;
import java.security.Permissions;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_bar);

//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
       PermissionsUtil.checkAndRequestPermissions(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){

            Log.e("--->>","执行了");
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                File file = new File(Environment.getExternalStorageDirectory(),"2.txt");

                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
