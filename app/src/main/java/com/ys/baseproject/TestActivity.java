package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.ys.baseproject.base.BaseAdapter;
import com.ys.baseproject.recyclerview.LRecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by yunshan on 17/3/29.
 */

public class TestActivity extends AppCompatActivity{

    private List<User> userList;
    private SelectAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        
        recyclerView = (RecyclerView) findViewById(R.id.lRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectAdapter();
        recyclerView.setAdapter(adapter);
        loadData();

        adapter.setUserList(userList);
    }

    public void loadData() {
         userList = new ArrayList<>();
//        userList.add(new User("1111","11",false));
//        userList.add(new User("1112","11",false));
//        userList.add(new User("1113","11",false));
//        userList.add(new User("1114","11",false));
//        userList.add(new User("1115","11",false));
//        userList.add(new User("1116","11",false));
//        userList.add(new User("1117","11",false));
//        userList.add(new User("1118","11",false));
//        userList.add(new User("1119","11",false));
    }
}
