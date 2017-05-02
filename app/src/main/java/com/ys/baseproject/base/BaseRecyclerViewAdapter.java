package com.ys.baseproject.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/3/24.
 * <p>
 * 添加多个头部的adapter
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> headerTypes = new ArrayList<>();    //定义头部类型的集合

    private List<View> mHeaderViews = new ArrayList<>();     //头部view集合
    private List<View> mFooterViews = new ArrayList<>();    //尾部view集合

    public static final int HEAD_VIEW_INDEX = 10002;
    public static final int FOOTER_VIEW_TYPE = 10001;

    private RecyclerView.Adapter mInnerAdapter;

    public void addHeaderView(View view) {
        if (view == null) {
            throw new IllegalStateException("headerview is null");
        }
        headerTypes.add(HEAD_VIEW_INDEX + mHeaderViews.size());

        mHeaderViews.add(view);
    }

    public View getHeaderByType(int itemType){
        if(!isHeaderViewType(itemType)){
            return null;
        }
        return mHeaderViews.get(itemType - HEAD_VIEW_INDEX);
    }

    public void addFooterView(View view) {
        if (view == null) {
            throw new IllegalStateException("footerview is null");
        }
        removeFooterView();
        mFooterViews.add(view);
    }

    public void removeFooterView() {
        if (getFooterViewCount() > 0) {
            View footerView = getFooterView();
            mFooterViews.remove(footerView);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(){
        if(getHeaderViewCount()>0){
            View headerView = getHeaderView();
            mHeaderViews.remove(headerView);
            notifyDataSetChanged();
        }
    }

    public boolean isHeaderViewType(int itemType){
        return mHeaderViews.size()>0 && headerTypes.contains(itemType);
    }

    public boolean isHeader(int position){
        return position >= 0 && position <mHeaderViews.size();
    }

    public boolean isFooter(int position){
        int lastPosition = getItemCount() - getFooterViewCount();
        return getFooterViewCount() >0 && position >lastPosition;
    }


    public int getFooterViewCount() {
        return mFooterViews.size();
    }

    public int getHeaderViewCount() {
        return mHeaderViews.size();
    }

    //返回第一个footerView
    public View getFooterView() {
        return getFooterViewCount() > 0 ? mFooterViews.get(0) : null;
    }

    //返回第一个HeaderView
    public View getHeaderView() {
        return getHeaderViewCount() > 0 ? mHeaderViews.get(0) : null;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);

        if(isHeader(position)){
            return headerTypes.get(position);
        }

        if(isFooter(position)){
            return FOOTER_VIEW_TYPE;
        }
        if(mInnerAdapter != null) {
            int adapterCount = mInnerAdapter.getItemCount();
        }


        return -1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
