package com.ys.baseproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yunshan on 17/3/31.
 */

public class FixedGridLayoutManager extends RecyclerView.LayoutManager{


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        View scrap = recycler.getViewForPosition(0);

        super.onLayoutChildren(recycler, state);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }
}
