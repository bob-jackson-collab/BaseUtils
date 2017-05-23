package com.ys.baseproject.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/5/18.
 */

public class NestListView extends LinearLayout {

    private LayoutInflater mInflater;
    private List<NestFullViewHolder> mVHCahces;
    private NestFullListViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener;

    public NestListView(Context context) {
        this(context, null);
    }

    public NestListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mInflater = LayoutInflater.from(context);
        mVHCahces = new ArrayList<>();
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public void setAdapter(NestFullListViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
        updateUI();
    }


    public void updateUI() {
        if (null != mAdapter) {
            if (null != mAdapter.getDatas() && !mAdapter.getDatas().isEmpty()) {
                //数据源有数据
                if (mAdapter.getDatas().size() > getChildCount() - getFooterCount()) {//数据源大于现有子View不清空

                } else if (mAdapter.getDatas().size() < getChildCount() - getFooterCount()) {//数据源小于现有子View，删除后面多的
                    removeViews(mAdapter.getDatas().size(), getChildCount() - mAdapter.getDatas().size() - getFooterCount());
                    //删除View也清缓存
                    while (mVHCahces.size() > mAdapter.getDatas().size()) {
                        mVHCahces.remove(mVHCahces.size() - 1);
                    }
                }
                for (int i = 0; i < mAdapter.getDatas().size(); i++) {
                    NestFullViewHolder holder;
                    if (mVHCahces.size() - 1 >= i) { //说明有缓存，不用inflate，否则inflate
                        holder = mVHCahces.get(i);
                    } else {
                        holder = new NestFullViewHolder(getContext(), mInflater.inflate(mAdapter.getItemLayoutId(), this, false));
                        mVHCahces.add(holder);//inflate 出来后 add进来缓存
                    }
                    mAdapter.onBind(i, holder);
                    //如果View没有父控件 添加
                    if (null == holder.getConvertView().getParent()) {
                        this.addView(holder.getConvertView(), getChildCount() - getFooterCount());
                    }

                    /* 增加子项点击事件 */
                    final int mPosition = i;
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null && mAdapter != null) {
                                mOnItemClickListener.onItemClick(NestListView.this, v, mPosition);
                            }
                        }
                    });
                }
            } else {
                removeViews(0, getChildCount() - getFooterCount());//数据源没数据 清空视图
            }
        } else {
            removeViews(0, getChildCount() - getFooterCount());//适配器为空 清空视图
        }
    }

    /**
     * 子项点击事件的接口
     */
    public interface OnItemClickListener {

        void onItemClick(NestListView parent, View view, int position);
    }

    public int getFooterCount() {
        return 0;
    }
}
