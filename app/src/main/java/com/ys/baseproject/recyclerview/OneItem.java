package com.ys.baseproject.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.ys.baseproject.CityBean;
import com.ys.baseproject.R;
import com.ys.baseproject.TwoCityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public class OneItem extends TreeAdapterItem<CityBean>{

    public OneItem(CityBean data) {
        super(data);
    }

    @Override
    public List<TreeAdapterItem> initChildsList(CityBean data) {
        ArrayList<TreeAdapterItem> oneChilds = new ArrayList<>();
        List<TwoCityBean> cityBeen = data.getBeanList();
        if(cityBeen ==null){
            return null;
        }
        for(int i= 0;i<cityBeen.size();i++){
           TwoItem twoItem = new TwoItem(cityBeen.get(i));
            oneChilds.add(twoItem);
        }
        return oneChilds;
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_tree;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.text_tree,data.getCity());
    }
}
