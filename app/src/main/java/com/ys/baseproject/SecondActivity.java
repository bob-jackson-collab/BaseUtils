package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ys.baseproject.rx.RxBus;

import rx.functions.Action1;

/**
 * Created by yunshan on 17/3/14.
 */

public class SecondActivity extends AppCompatActivity{

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);


        fragment1 = new Fragment1();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment1);
        transaction.addToBackStack(fragment1.getClass().getSimpleName());
        transaction.commit();
//        getSupportFragmentManager().beginTransaction().replace().commit();


        RxBus.getRxBus().register("123",Fragment2.class).subscribe(new Action1<Fragment2>() {
            @Override
            public void call(Fragment2 fragment2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment2).commit();
            }
        });

    }
}
