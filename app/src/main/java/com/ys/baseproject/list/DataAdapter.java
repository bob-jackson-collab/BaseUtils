package com.ys.baseproject.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ys.baseproject.R;
import com.ys.baseproject.db.ChildData;
import com.ys.baseproject.db.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/5/19.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<Data> mDatas;
    private OnChildItemClickListener listener;

    public DataAdapter() {
        mDatas = new ArrayList<>();
    }

    public void setDatas(List<Data> mDatas) {
        if (mDatas != null && mDatas.size() > 0) {
            this.mDatas = mDatas;
            notifyDataSetChanged();
        }
    }

    public void setOnChildItemClickListener(OnChildItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        DataViewHolder holder = new DataViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        Data data = mDatas.get(position);
        if (data != null) {
            holder.text_fcode.setText(mDatas.get(position).getNumber());

            List<ChildData> childDatas = data.getDatas();

            if (childDatas != null && childDatas.size() > 0) {
                holder.nestListView.setAdapter(new NestFullListViewAdapter<ChildData>(R.layout.item_second, childDatas) {

                    @Override
                    public void onBind(final int pos, ChildData childData, final NestFullViewHolder holder) {

//                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (listener != null) {
//                                    listener.onChildItemClick(position, pos, holder);
//                                }
//                            }
//                        });

                        if (childData.isFlag()) {
                            holder.setText(R.id.tv_data, "已核货");
                        } else {
                            holder.setText(R.id.tv_data, "未核货");
                        }

                        holder.getView(R.id.tv_data).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (listener != null) {
                                    listener.onChildItemClick(position, pos, holder);
                                }
                            }
                        });

                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView text_fcode;
        private NestListView nestListView;

        public DataViewHolder(View itemView) {
            super(itemView);
            text_fcode = (TextView) itemView.findViewById(R.id.tv_data);
            nestListView = (NestListView) itemView.findViewById(R.id.nestView);
        }
    }

    public interface OnChildItemClickListener {
        // childview中的position  操作中的hoder
        void onChildItemClick(int groupPosition, int childPosition, NestFullViewHolder holder);
    }
}
