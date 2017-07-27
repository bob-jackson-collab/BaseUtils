package com.ys.baseproject.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yunshan on 17/6/29.
 */

//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("NewApi")
public class JobHandlerService extends JobService {

    private JobScheduler mJobScheduler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //如果大于5.0版本
            mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(startId++,
                    new ComponentName(getPackageName(), JobHandlerService.class.getName()));
            builder.setPeriodic(5000); //每隔5秒运行一次
            builder.setRequiresCharging(true);
            builder.setPersisted(true);  //设置设备重启后，是否重新执行任务
            builder.setRequiresDeviceIdle(true);
//            setRequiresCharging：是否在充电时执行
//            setRequiresDeviceIdle：是否在空闲时执行
//            PermissionUtils.requestPermission();
            if (mJobScheduler.schedule(builder.build()) == JobScheduler.RESULT_SUCCESS) {
                System.out.println("运行成功");
            } else {
                System.out.println("运行失败");
            }
        }
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "服务启动", Toast.LENGTH_SHORT).show();

        System.out.println("开始工作");
        if (!isServiceRunning(this, "com.ys.baseproject.service.LocalService") ||
                !isServiceRunning(this, "com.ys.baseproject.service.RemoteService")) {
            startService(new Intent(this, LocalService.class));
            startService(new Intent(this, RemoteService.class));
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }

    // 服务是否运行
    public boolean isServiceRunning(Context context, String serviceName) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo info : lists) {// 获取运行服务再启动
            System.out.println(info.processName);
            if (info.processName.equals(serviceName)) {
                isRunning = true;
            }
        }
        return isRunning;

    }


}
