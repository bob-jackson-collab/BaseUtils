package com.ys.baseproject.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by yunshan on 17/3/13.
 */

public class RxActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Subscription subscribe = Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread()).subscribe();


    }
}
