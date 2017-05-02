package com.ys.baseproject.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.ys.baseproject.R;
import com.ys.baseproject.TwoCityBean;

import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public class TwoItem extends TreeAdapterItem<TwoCityBean>{

    public TwoItem(TwoCityBean data) {
        super(data);
    }

    @Override
    public List<TreeAdapterItem> initChildsList(TwoCityBean data) {
        return null;
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_tree;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
       holder.setText(R.id.text_tree,data.getCode());
    }
}
