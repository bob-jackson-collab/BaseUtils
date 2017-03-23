package com.ys.baseproject.rx;

import rx.Subscriber;

/**
 * Created by yunshan on 17/3/23.
 * 一般来很少用onCompleted方法主要就是专门提出来 保持代码的简洁性
 */

public class RxSubScriber<T> extends Subscriber<T>{

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
