package com.ys.baseproject.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ys.baseproject.R;

import butterknife.ButterKnife;

import static android.R.attr.fragment;

/**
 * Created by Administrator on 2017/2/24.
 */

public class H5Activity extends BaseActivity{


    private H5Fragment h5Fragment;

    @Override
    public void handleLayoutAndData() {

        h5Fragment = new H5Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,h5Fragment);


        Bundle bundle = new Bundle();
        
        h5Fragment.setArguments(bundle);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_h5;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction().remove(h5Fragment);
    }
}
