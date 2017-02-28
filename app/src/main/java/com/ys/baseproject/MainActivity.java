package com.ys.baseproject;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.ys.baseproject.utils.PermissionsUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_bar);

//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
        PermissionsUtil.checkAndRequestPermissions(this);

//        Arrays.asList()
//        this.openFileOutput("",MODE_PRIVATE)

        Observable.just("1", "2", "3").map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) throws Exception {
                return Integer.valueOf(s);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("integer", integer + "");
            }
        });


        List<String> list = new ArrayList<>(5);
        list.add("java");
        list.add("Android");
        list.add("C");
        list.add("C++");
        list.add("php");



        Observable<List<String>> just = Observable.just(list);
        just.flatMap(new Function<List<String>, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> apply(List<String> strings) throws Exception {
                 return Observable.fromArray(strings);
            }
        }).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {

            }
        });

//        Observable.just(list).filter(new Predicate<List<String>>() {
//            @Override
//            public boolean test(List<String> strings) throws Exception {
//                return false;
//            }
//        }).take(3).subscribe(new Consumer<List<String>>() {
//            @Override
//            public void accept(List<String> strings) throws Exception {
//
//            }
//        })
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {

            Log.e("--->>", "执行了");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                File file = new File(Environment.getExternalStorageDirectory(), "2.txt");

                if (!file.exists()) {
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
