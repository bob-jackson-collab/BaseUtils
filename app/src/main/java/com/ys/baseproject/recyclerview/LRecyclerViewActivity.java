package com.ys.baseproject.recyclerview;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.ys.baseproject.R;
import com.ys.baseproject.base.BaseActivity;
import com.ys.baseproject.base.BaseAdapter;

/**
 * Created by yangsong on 2017/1/12.
 * 继承该类 实现下拉刷新和上拉加载
 */

public abstract class LRecyclerViewActivity extends BaseActivity implements OnItemClickListener,OnRefreshListener,OnLoadMoreListener {

    public abstract BaseAdapter getBaseAdapter();

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract void loadData();

    private Toolbar toolbar;
    public LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

//    private LinearLayoutManager linearLayoutManager;
//    private GridLayoutManager gridLayoutManager;
//    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public int getContentViewId() {
        return R.layout.base_recycler_layout;
    }

    @Override
    public void handleLayoutAndData() {
        lRecyclerView = (LRecyclerView) findViewById(R.id.lRecyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setLRecyclerView();

        loadData();
    }

    private void setLRecyclerView(){
//        linearLayoutManager = new LinearLayoutManager(mContext);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(getBaseAdapter());
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        //设置刷新风格
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        //可以进行下拉刷新和上拉加载
        lRecyclerView.setPullRefreshEnabled(true);
        lRecyclerView.setLoadMoreEnabled(true);
        //设置布局
        lRecyclerView.setLayoutManager(getLayoutManager());
        //设置LRecyclerView自适应布局
        lRecyclerView.setHasFixedSize(true);
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        //设置LRecyclerView分割线
        lRecyclerView.addItemDecoration(divider);
        lRecyclerView.setOnRefreshListener(this);
        lRecyclerView.setOnLoadMoreListener(this);
        lRecyclerViewAdapter.setOnItemClickListener(this);
    }

    /**
     * Item的点击事件
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {

    }

    public void notifyDataSetChanged(){
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
