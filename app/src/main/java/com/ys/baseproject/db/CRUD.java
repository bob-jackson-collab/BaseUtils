package com.ys.baseproject.db;

import java.util.List;

import rx.Observable;

/**
 * Created by yunshan on 17/5/23.
 */

public interface CRUD {

    Observable<Boolean> insertStatus(Status status);

    Observable<Boolean> inseertStatuss(List<Status> statusList);

    Observable<List<Status>> queryAll();

    Observable<Status> queryFromId(int id);

    Observable<Boolean> deleteAllStatus(List<Status> statuses);

}
