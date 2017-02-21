package com.ys.baseproject.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/1/12.
 * 定时器类
 */

public abstract class FunctionTimerTask extends TimerTask{

    private Timer mTimer;
    private FunctionTimerTask mTask;
    private long period;

    public abstract void runTask();

    public FunctionTimerTask(Timer timer, long period){
        mTimer = timer;
        this.period = period;
    }

    @Override
    public void run() {
         runTask();
    }

    public void start(){
       if(mTimer!=null){
           mTimer.schedule(this,0,period);
       }
    }

    public void cancelTask(){
        this.cancel();
    }
}
