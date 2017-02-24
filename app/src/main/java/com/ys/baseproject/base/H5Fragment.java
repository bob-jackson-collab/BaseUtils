package com.ys.baseproject.base;

import android.webkit.WebView;

import com.ys.baseproject.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/2/24.
 */

public class H5Fragment extends BaseFragment{

    @BindView(R.id.webView)
    private WebView webView;

    private H5Activity h5Activity;

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.frag_h5;
    }

    @Override
    public void initData() {
        super.initData();

        if(getActivity() instanceof H5Activity){
            h5Activity = (H5Activity) getActivity();
        }


    }
}
