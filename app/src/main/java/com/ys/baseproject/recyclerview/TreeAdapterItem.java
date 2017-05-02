package com.ys.baseproject.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public abstract class TreeAdapterItem<D> {
    /**
     * 当前Item的数据
     */
    protected D data;
    /**
     * 持有的子数据
     */
    protected List<TreeAdapterItem> childs;
    /**
     * 是否展开
     */
    protected boolean isExpand;
    /**
     * 布局资源id
     */
    protected int layoutId;

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public List<TreeAdapterItem> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeAdapterItem> childs) {
        this.childs = childs;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public TreeAdapterItem(D data){
        this.data = data;
        childs = initChildsList(data);
        layoutId = initLayoutId();
    }

    /**
     * 展开
     */
    public void onExpand(){
        isExpand = true;
    }

    /**
     * 折叠
     */
    public void onCollapse(){
        isExpand = false;
    }

    /**
     * 是否有子数据
     */
    public boolean isParent(){
        return childs != null && childs.size() > 0;
    }

    /**
     *递归遍历所有的字数据，包括子数据的子数据
     */
     public List<TreeAdapterItem> getAllChilds(){
         ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
         for(int i = 0;i<childs.size(); i++){
             TreeAdapterItem treeAdapterItem = childs.get(i);
             treeAdapterItems.add(treeAdapterItem);
             if(treeAdapterItem.isParent()){
                 List list = treeAdapterItem.getAllChilds();
                 if(list !=null && list.size() >0){
                     treeAdapterItems.addAll(list);
                 }
             }
         }
         return treeAdapterItems;
     }

    public abstract List<TreeAdapterItem> initChildsList(D data);
    public abstract int initLayoutId();

    /**
     * 抽象holder的绑定
     * @param holder
     */
    public abstract void onBindViewHolder(ViewHolder holder);

}
