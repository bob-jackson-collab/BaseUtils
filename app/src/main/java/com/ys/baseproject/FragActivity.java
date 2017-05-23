package com.ys.baseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/5/8.
 */

public class FragActivity extends AppCompatActivity{


    private Fragment1 fragment1;
    private ViewPager pager;
    private TabLayout tabLayout;
    private List<String> titles;
    private List<Fragment> list;
    private NuclearGoodsTabAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        titles = new ArrayList<>();
        titles.add("1");
        titles.add("2");
        pager = (ViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(Fragment2.getNewInstance("111"));
        mAdapter = new NuclearGoodsTabAdapter(getSupportFragmentManager(),titles,list);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabTextColors(Color.RED,Color.BLACK);
        tabLayout.setTabTextColors(Color.RED,Color.BLACK);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(pager);
//        setupTabIcons();


//        fragment1 = new Fragment1();
//        getSupportFragmentManager().beginTransaction().add(R.id.frame,fragment1).commit();
    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));
//        tabLayout.getTabAt(2).setCustomView(getTabView(2));
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.title);
        txt_title.setText(titles.get(position));
        return view;
    }
}
