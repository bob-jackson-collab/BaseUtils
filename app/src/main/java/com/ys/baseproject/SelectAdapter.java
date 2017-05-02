package com.ys.baseproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/3/31.
 */

public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<User> userList;
    private int mSelectedPosition = -1;

    public SelectAdapter(){
        userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList){
        this.userList = userList;
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).isSelected()){
                mSelectedPosition = i;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        final SelectViewHolder holder = new SelectViewHolder(itemView);

        holder.iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userList.get(holder.getLayoutPosition()).setSelected
                        (!userList.get(holder.getLayoutPosition()).isSelected());
                notifyItemChanged(holder.getLayoutPosition());
//                if(userList.get(holder.getLayoutPosition()).isSelected()){
//                    userList.get(holder.getLayoutPosition()).setSelected(false);
//                    notifyItemChanged(holder.getLayoutPosition());
//                }


//                if(!userList.get(holder.getLayoutPosition()).isSelected()){
//                    userList.get(holder.getLayoutPosition()).setSelected(true);
//                    notifyItemChanged(holder.getLayoutPosition());
//                    mSelectedPosition = holder.getLayoutPosition();
//                }


//                if(mSelectedPosition != holder.getLayoutPosition()){
//                    if(mSelectedPosition != -1) {
//                        userList.get(mSelectedPosition).setSelected(false);
//                        notifyItemChanged(mSelectedPosition);
//                    }
//
//                    mSelectedPosition = holder.getLayoutPosition();
//                    userList.get(mSelectedPosition).setSelected(true);
//                    notifyItemChanged(mSelectedPosition);
//                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SelectViewHolder holder1 = (SelectViewHolder) holder;
        holder1.iv_select.setSelected(userList.get(position).isSelected());
        holder1.tv_select.setText(userList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class SelectViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_select;
        private TextView tv_select;

        public SelectViewHolder(View itemView) {
            super(itemView);
            iv_select = (ImageView) itemView.findViewById(R.id.iv_select);
            tv_select = (TextView) itemView.findViewById(R.id.tv_select);
        }

    }
}
