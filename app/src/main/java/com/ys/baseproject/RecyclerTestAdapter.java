package com.ys.baseproject;

import android.content.Context;

import com.ys.baseproject.base.BaseAdapter;
import com.ys.baseproject.base.BaseViewHolder;

/**
 * Created by yunshan on 17/3/28.
 */

public class RecyclerTestAdapter extends BaseAdapter<String,BaseViewHolder>{

    public RecyclerTestAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item;
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, String item) {
       viewHoder.getTextView(R.id.text).setText(item);
    }
}
