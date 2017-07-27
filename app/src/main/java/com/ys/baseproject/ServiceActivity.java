package com.ys.baseproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ys.baseproject.service.JobHandlerService;

/**
 * Created by yunshan on 17/6/29.
 */

public class ServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            openJobService();
        }
    }

    private void openJobService() {

        Intent intent = new Intent();
        intent.setClass(ServiceActivity.this, JobHandlerService.class);
        startService(intent);

    }
}
