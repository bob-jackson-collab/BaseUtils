package com.ys.baseproject.rx;

import com.ys.baseproject.base.BaseEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yunshan on 17/3/13.
 */

public class RxUtils {

    //
    public static <T> Observable.Transformer<T , T> appSchedulers(){

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static  <T> Observable.Transformer<BaseEntity<T>,T> vailate(){
        return new Observable.Transformer<BaseEntity<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseEntity<T>> baseEntityObservable) {
                return baseEntityObservable.flatMap(new Func1<BaseEntity<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity<T> tBaseEntity) {
                        if(tBaseEntity == null){
                            throw new RuntimeException("获取内容失败");
                        }
                        if(tBaseEntity.getError_code() == 0){  //假设0代表请求数据成功
                            return Observable.just(tBaseEntity.getData());
                        }else{
                            throw new RuntimeException(tBaseEntity.getMessage());
                        }
                    }
                });
            }
        };
    }
}
