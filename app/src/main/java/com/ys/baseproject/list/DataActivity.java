package com.ys.baseproject.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;
import com.ys.baseproject.R;
import com.ys.baseproject.db.ChildData;
import com.ys.baseproject.db.Data;
import com.ys.baseproject.db.DataDao;
import com.ys.baseproject.db.DatabaseHelper;
import com.ys.baseproject.db.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yunshan on 17/5/18.
 */

public class DataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    private List<Data> mDatas;
    private LinearLayoutManager manager;
    private List<Status> statuses;
    private DataDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DataAdapter();
        recyclerView.setAdapter(mAdapter);
        dao = new DataDao(this);

        statuses = new ArrayList<>();
        mDatas = new ArrayList<>();

        List<ChildData> list1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ChildData childData = new ChildData();
            childData.setData((i + 1) + "");
            childData.setId((i + 1) + "");
            childData.setFlag(true);
            list1.add(childData);
        }

        List<ChildData> list2 = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            ChildData childData = new ChildData();
            childData.setData((i + 1) + "");
            childData.setFlag(false);
            childData.setId((i + 1) + "");
            list2.add(childData);
        }

        Data data1 = new Data();
        data1.setNumber("3333333");
        data1.setDatas(list1);

        Data data2 = new Data();
        data2.setDatas(list2);
        data2.setNumber("4444444");


        mDatas.add(data1);
        mDatas.add(data2);

        mAdapter.setDatas(mDatas);


        mAdapter.setOnChildItemClickListener(new DataAdapter.OnChildItemClickListener() {
            @Override
            public void onChildItemClick(int groupPosition, int childPosition, NestFullViewHolder holder) {

                ChildData childData = mDatas.get(groupPosition).getDatas().get(childPosition);

                childData.setFlag(!childData.isFlag());

                mAdapter.notifyItemChanged(groupPosition);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onpause","onPause");

        Observable.create(new Observable.OnSubscribe<Boolean>(){

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                for (Data data : mDatas) {
                    for (ChildData childData : data.getDatas()) {
                        Status status = new Status();
                        status.setData_id(childData.getId());
                        status.setStatus(childData.isFlag());
                        dao.addUser(status);
                    }
                }
                subscriber.onNext(true);
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
//                showLoad();
            }
        }).doOnTerminate(new Action0() {
            @Override
            public void call() {
//                hide();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                    }
                });


        statuses = dao.queryAll();
        if(statuses!=null && statuses.size()>0){
            for(Status status:statuses){
                dao.addUser(status);
            }
        }else {
            //保存数据
            for (Data data : mDatas) {
                for (ChildData childData : data.getDatas()) {
                    Status status = new Status();
                    status.setData_id(childData.getId());
                    status.setStatus(childData.isFlag());
                    dao.addUser(status);
                }
            }
        }
    }

}
