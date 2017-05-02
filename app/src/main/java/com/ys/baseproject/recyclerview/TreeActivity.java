package com.ys.baseproject.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ys.baseproject.CityBean;
import com.ys.baseproject.R;
import com.ys.baseproject.TwoCityBean;
import com.ys.baseproject.net.IPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public class TreeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        IPUtils ipUtils = new IPUtils(this);


        Log.e("ipv4",ipUtils.getIpV4());

        Log.e("Ipvvvv4",ipUtils.getIpAddressString());


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CityBean> list = new ArrayList<>();
        List<TwoCityBean> list1 = new ArrayList<>();

        for(int i= 0;i<10;i++) {
            list1.add(new TwoCityBean("112"));
        }

        for(int i= 0;i<10;i++){
            list.add(new CityBean(i+"北京",list1));
        }

        List<TwoItem> twoItems = new ArrayList<>();
        List<OneItem> oneItems = new ArrayList<>();
        for(int i= 0;i<list.size();i++){
            OneItem oneItem= new OneItem(list.get(i));
            oneItems.add(oneItem);
        }

        for(int i=0;i<list1.size();i++){
            TwoItem twoItem = new TwoItem(list1.get(i));
            twoItems.add(twoItem);
        }


        final TreeRecyclerViewAdapter mAdapter = new TreeRecyclerViewAdapter(this,oneItems);

        recyclerView.setAdapter(mAdapter);
    }
}
