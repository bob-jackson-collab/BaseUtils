package com.ys.baseproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yunshan on 17/5/10.
 */

public class NuclearGoodsTabAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private List<Fragment> fragmentList;

    public NuclearGoodsTabAdapter(FragmentManager fm, List<String> mTitles, List<Fragment> fragmentList) {
        this(fm);
        this.mTitles = mTitles;
        this.fragmentList = fragmentList;
    }

    public NuclearGoodsTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles !=null && mTitles.size() > 0)
        return mTitles.get(position);
        return null;
    }
}
