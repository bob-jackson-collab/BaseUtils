package com.ys.baseproject.rx;

import android.view.View;

import com.ys.baseproject.base.BaseEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yunshan on 17/3/13.
 */

public class RxUtils {

    //
    public static <T> Observable.Transformer<T, T> appSchedulers() {

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<BaseEntity<T>, T> vailate() {
        return new Observable.Transformer<BaseEntity<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseEntity<T>> baseEntityObservable) {
                return baseEntityObservable.flatMap(new Func1<BaseEntity<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity<T> tBaseEntity) {
                        if (tBaseEntity == null) {
                            throw new RuntimeException("获取内容失败");
                        }
                        if (tBaseEntity.getError_code() == 0) {  //假设0代表请求数据成功
                            return Observable.just(tBaseEntity.getData());
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(tBaseEntity.getError_code()+":").append(tBaseEntity.getMessage());
                            return Observable.error(new RuntimeException());
//                            throw new RuntimeException(tBaseEntity.getMessage());
                        }
                    }
                });
            }
        };
    }

    //显示加载的进度条
    public static <T> Observable.Transformer<T, T> showLoading(View view) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示进度条
//                        view.showLoading();
                    }
                }).doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        //隐藏进度条
//                        view.hideLoading();
                    }
                }).observeOn(AndroidSchedulers.mainThread());  //只能在UI线程操作view
            }
        };
    }
}
