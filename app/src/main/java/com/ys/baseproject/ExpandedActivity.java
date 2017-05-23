package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.blankj.utilcode.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by yunshan on 17/4/28.
 */

public class ExpandedActivity extends AppCompatActivity{

    private ExpandableListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        mListView = (ExpandableListView) findViewById(R.id.expande_list);
        //取消展开的标识
        mListView.setGroupIndicator(null);

        ArrayList<String> groupList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            groupList.add(String.valueOf(i));
        }

        ArrayList<String> childList = new ArrayList<>();
        for(int i=20;i>10;i--){
            childList.add(String.valueOf(i));
        }

        ExpanedableAdapter adapter = new ExpanedableAdapter(groupList,childList);
        mListView.setAdapter(adapter);

        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                 Log.e("onfo",childPosition+"");
                return true;
            }
        });

        //默认第一项展开
        mListView.expandGroup(0);
    }
}
