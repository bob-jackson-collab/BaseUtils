package com.ys.baseproject.db;

import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yunshan on 17/5/23.
 */

public class DBManager implements CRUD {

    private DataDao dataDao;

    @Override
    public Observable<Boolean> insertStatus(final Status status) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    dataDao.addUser(status);
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                } catch (Exception e) {

                }


            }
        });
    }

    @Override
    public Observable<Boolean> inseertStatuss(final List<Status> statusList) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    dataDao.addUsers(statusList);
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                }catch (Exception e){

                }
            }
        });
    }

    @Override
    public Observable<List<Status>> queryAll() {
        return Observable.create(new Observable.OnSubscribe<List<Status>>() {
            @Override
            public void call(Subscriber<? super List<Status>> subscriber) {
                try {

                    List<Status> statuses = dataDao.queryAll();
                    if (statuses != null) {
                        subscriber.onNext(statuses);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public Observable<Status> queryFromId(final int id) {
        return Observable.create(new Observable.OnSubscribe<Status>() {
            @Override
            public void call(Subscriber<? super Status> subscriber) {
                try {
                    Status status = dataDao.queryUser(id);
                    subscriber.onNext(status);
                    subscriber.onCompleted();
                }catch (Exception e){

                }
            }
        });
    }

    @Override
    public Observable<Boolean> deleteAllStatus(final List<Status> status) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    dataDao.deleteAllUser(status);
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                }catch (Exception e){

                }
            }
        });
    }
}
