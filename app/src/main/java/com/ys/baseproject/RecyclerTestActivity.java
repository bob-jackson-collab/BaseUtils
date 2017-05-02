package com.ys.baseproject;

import android.app.Service;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.utils.NetworkUtils;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.ys.baseproject.base.BaseAdapter;
import com.ys.baseproject.base.BaseViewHolder;
import com.ys.baseproject.recyclerview.LRecyclerViewActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yunshan on 17/3/28.
 */

public class RecyclerTestActivity extends LRecyclerViewActivity {

    private List<String> datas;
    private List<String> lists;
    private RecyclerTestAdapter adapter;

    private ItemTouchHelper itemTouchHelper;

    @Override
    public BaseAdapter getBaseAdapter() {
        adapter = new RecyclerTestAdapter(this);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void loadData() {
        int array[] = new int[2];

        itemTouchHelper = new ItemTouchHelper(new ItemCallBack());
        itemTouchHelper.attachToRecyclerView(lRecyclerView);
        lists = new ArrayList<>(10);
        datas = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            datas.add(String.valueOf(i));
        }


        lRecyclerView.refresh();
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);

    }

    @Override
    public void onItemLongClick(View view, int position) {
//        Log.e("info", String.valueOf(position));
//        //获取系统震动服务
        Vibrator vib = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
//        //震动70毫秒
        vib.vibrate(70);
        if (position != lists.size() - 1) {
            itemTouchHelper.startDrag(lRecyclerView.getChildViewHolder(view));
        }

        super.onItemLongClick(view, position);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        adapter.clear();
        notifyDataSetChanged();

        adapter.refreshData(datas);
        notifyDataSetChanged();
        lRecyclerView.refreshComplete(10);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();

        for (int i = 10; i < 20; i++) {
            lists.add(String.valueOf(i));
        }

        if (!NetworkUtils.isConnected()) {
            lRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                @Override
                public void reload() {
                    adapter.addData(lists);
                    notifyDataSetChanged();
                }
            });
        }
    }

    class ItemCallBack extends ItemTouchHelper.Callback {


        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0, swipFlags = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipFlags = 0;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipFlags = 0;
            }
            return makeMovementFlags(dragFlags, swipFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            if(viewHolder.getItemViewType() != target.getItemViewType()){
                return false;
            }

//            if(lRecyclerView.getAdapter() instanceof OnItemM)

            int fromPosition = viewHolder.getAdapterPosition();
            int topPosition = target.getAdapterPosition();

            if (fromPosition < topPosition) {
                for (int i = fromPosition; i < topPosition; i++) {
                    Collections.swap(datas, i, i + 1);
                }
            } else if (topPosition < fromPosition) {
                for (int i = fromPosition; i > topPosition; i--) {
                    Collections.swap(datas, i, i - 1);
                }
            }
            adapter.refreshData(datas);
            adapter.notifyItemMoved(fromPosition, topPosition);

            lRecyclerViewAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
//                viewHolder.itemView.setBackgroundColor(Color.RED);

            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//            viewHolder.itemView.setBackgroundColor(0);
            super.clearView(recyclerView, viewHolder);
        }

        //拖拽滑动我们自己控制
        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        //不支持滑动
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    }
}
