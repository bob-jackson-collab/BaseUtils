package com.ys.baseproject.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/7.
 */

public class BaseFragment extends Fragment implements IBaseFragment{

    private View inflateView;

    private Activity activity;

    private boolean viewCreated;

    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //处理相关数据逻辑
        if (!viewCreated) {
            viewCreated = true;
            initData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        isFirstLoad = true;

        isPrepared = true;
        if (null == inflateView) {
            // 强制竖屏显示
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            int layoutResId = getCreateViewLayoutId();
            if (layoutResId > 0)
                inflateView = inflater.inflate(getCreateViewLayoutId(), container, false);

            ButterKnife.bind(this,inflateView);
            // 解决点击穿透问题
            inflateView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
        initEventAndData();
        //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据,GankFragment有使用实例
        lazyLoad();

        return inflateView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewCreated) {
            findView(view, savedInstanceState);
            initView(view, savedInstanceState);
            initDialog();
            initListener();
            viewCreated = false;
        }
    }

    /**
     * Case 1：当使用Fragment去嵌套另外一些子Fragment的时候，我们需要去管理子Fragment，这时候需要调用ChildFragmentManager去管理这些子Fragment，由此可能产生的Exception主要是：
     java.lang.IllegalStateException: No activity

     首先我们来分析一下Exception出现的原因：
     通过DEBUG发现，当第一次从一个Activity启动Fragment，然后再去启动子Fragment的时候，存在指向Activity的变量，但当退出这些Fragment之后回到Activity，然后再进入Fragment的时候，这个变量变成null，这就很容易明了为什么抛出的异常是No activity

     这个Exception是由什么原因造成的呢？如果想知道造成异常的原因，那就必须去看Fragment的相关代码，发现Fragment在detached之后都会被reset掉，但是它并没有对ChildFragmentManager做reset，所以会造成ChildFragmentManager的状态错误。

     找到异常出现的原因后就可以很容易的去解决问题了，我们需要在Fragment被detached的时候去重置ChildFragmentManager
     */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = android.support.v4.app.Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }


    protected void onVisible(){
          lazyLoad();
    }

    protected void onInvisible(){

    }

    // 如果view加载完成且视图可见且是第一次加载完
    protected void lazyLoad(){
        if(!isPrepared || !isVisible || !isFirstLoad) return;
        isFirstLoad = false;
        lazyLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(null!=inflateView){
            ((ViewGroup)inflateView.getParent()).removeView(inflateView);
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return 0;
    }



    @Override
    public void initData() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDialog() {

    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void lazyLoadData() {

    }


}
