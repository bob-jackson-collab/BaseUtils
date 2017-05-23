package com.ys.baseproject.list;

import java.util.List;

/**
 * Created by yunshan on 17/5/18.
 */

public abstract class NestFullListViewAdapter<T> {

    private int itemLayoutId;
    private List<T> datas;

    public NestFullListViewAdapter(int itemLayoutId, List<T> datas) {
        this.itemLayoutId = itemLayoutId;
        this.datas = datas;
    }

    //绑定数据
    public void onBind(int pos, NestFullViewHolder holder) {
        onBind(pos, datas.get(pos), holder);
    }

    public abstract void onBind(int pos, T t, NestFullViewHolder holder);

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public void setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
