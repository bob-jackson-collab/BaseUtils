package com.ys.baseproject.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ys.baseproject.R;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yunshan on 17/3/13.
 */

public class RxActivity extends AppCompatActivity{

    private TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        text = (TextView) findViewById(R.id.text);
        Subscription subscribe = Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });


        Observable.just(1,2).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e("info","准备");
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        try {
                            Thread.sleep(2000);
                             return String.valueOf(integer);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        text.setText(s);
                    }
                });
        //doOnNext  在执行onNext的时候触发
        //doOnSubscribe 在进行订阅的时候会触发回调
        //doOnUnSubscribe

//        doOnUnSubscribe则会在Subscriber进行反订阅的时候触发回调。
//        当一个Observable通过OnError或者OnCompleted结束的时候，会反订阅所有的Subscriber。
//        doOnTerminate   DoOnTerminate会在Observable结束前触发回调，无论是正常还是异常终止；


    }
}
