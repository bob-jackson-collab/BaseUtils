package com.ys.baseproject.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public class TreeRecyclerViewAdapter<T extends TreeAdapterItem> extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;

    /**
     * 存储所有可见的Node
     */
    protected List<T> mDatas;

    private OnTreeItemClickListener onTreeItemClickListener;

    public void setOnTreeItemClickListener(OnTreeItemClickListener onTreeItemClickListener) {
        this.onTreeItemClickListener = onTreeItemClickListener;
    }

    public TreeRecyclerViewAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    /**
     * 相应RecyclerView的点击事件 展开或关闭
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        TreeAdapterItem treeAdapterItem = mDatas.get(position);
        //判断点击的条目是否有下一级
        if (!treeAdapterItem.isParent()) {
            return;
        }
        //判断是否展开
        boolean expand = treeAdapterItem.isExpand();
        if (expand) {
            //获取所有的子数据
            List allChilds = treeAdapterItem.getAllChilds();
            mDatas.removeAll(allChilds);
            //告诉item，折叠
            treeAdapterItem.onCollapse();
        } else {
            //获取下一级的数据
            mDatas.addAll(position + 1, treeAdapterItem.getChilds());
            //告诉item 展开
            treeAdapterItem.onExpand();
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(mContext,parent,viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final TreeAdapterItem treeAdapterItem = mDatas.get(position);
        treeAdapterItem.onBindViewHolder(holder);
        if(position == 0){
            List allchilds = treeAdapterItem.getAllChilds();
            for(int i = 0;i<allchilds.size();i++){
                TwoItem twoItem = (TwoItem) allchilds.get(i);
                twoItem.onBindViewHolder(holder);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //折叠或展开
                expandOrCollapse(holder.getLayoutPosition());
//                if (onTreeItemClickListener != null) {
//                    //点击监听的回调，一般不是最后一级，不需要处理
//                    onTreeItemClickListener.onClick(treeAdapterItem, position);
//                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        //返回item的layoutId
        return mDatas.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {

        return mDatas == null?0:mDatas.size();
    }


    public interface OnTreeItemClickListener {

        void onClick(TreeAdapterItem node, int position);
    }
}
