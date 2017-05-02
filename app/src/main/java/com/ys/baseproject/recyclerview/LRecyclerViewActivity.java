package com.ys.baseproject.recyclerview;


import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnItemLongClickListener;
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

public abstract class LRecyclerViewActivity extends BaseActivity implements OnItemClickListener,OnRefreshListener,OnLoadMoreListener ,OnItemLongClickListener{

    public abstract BaseAdapter getBaseAdapter();

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract void loadData();

    private Toolbar toolbar;
    public LRecyclerView lRecyclerView;
    public LRecyclerViewAdapter lRecyclerViewAdapter;

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
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(2.0f)
                .setColor(Color.RED)
                .build();
//        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        //设置LRecyclerView分割线
        lRecyclerView.addItemDecoration(divider);
        lRecyclerView.setOnRefreshListener(this);
        lRecyclerView.setOnLoadMoreListener(this);
        lRecyclerViewAdapter.setOnItemClickListener(this);
        lRecyclerViewAdapter.setOnItemLongClickListener(this);
        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.colorPrimaryDark ,android.R.color.white);
        //设置底部加载颜色
        lRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.colorPrimaryDark ,android.R.color.white);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

    }

    /**
     * Item的点击事件
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

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
