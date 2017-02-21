package com.ys.baseproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.recker.flybanner.FlyBanner;
import com.ys.baseproject.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 * 主要针对带有banner图
 */

public class HeaderView {

    private FlyBanner flyBanner;
    private View headerView;


    public HeaderView(Context context){
        headerView = LayoutInflater.from(context).inflate(R.layout.base_header,null);
        initData(headerView);
    }

    public View getHeaderView(){
        if(headerView!=null){
            return headerView;
        }
        return null;
    }

    public void initData(View view){
       flyBanner = (FlyBanner) view.findViewById(R.id.flyBanner);
        //设置指示器可见
        flyBanner.setPointsIsVisible(true);
        //设置指示器位置为重
        flyBanner.setPoinstPosition(FlyBanner.CENTER);
    }

    // 设置图片地址
    public void setImageUrls(List<String> imageUrls){
        flyBanner.setImagesUrl(imageUrls);
    }
}
