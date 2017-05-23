package com.ys.baseproject.objectanim;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.ys.baseproject.R;
import com.ys.baseproject.User;
import com.ys.baseproject.databinding.ActivityObjectanimBinding;
import com.ys.baseproject.db.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/5/2.
 */

public class ObjectAnimActivity extends AppCompatActivity{

    private ActivityObjectanimBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_objectanim);
        mBinding.setView(this);

        UserDao userDao = new UserDao(getApplicationContext());

//        DeleteBuilder deleteBuilder = userDao.
//        try {
//            userDao.getBuilder().where().eq("userName","yangsong0");
//            userDao.getBuilder().delete();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
        List<User> list = new ArrayList();

        list.add(new User("yangsong"+55555,"age"+555555));
        list.add(new User("yangsong"+1111555,"age"+555555));
        list.add(new User("yangsong"+5666555,"age"+555555));


        userDao.addUsers(list);
//        userDao.addUser(new User("yangsong"+5552225,"age"+55511155));
//        List<User> users = userDao.queryAll();
//
//        Log.e("user",users.size()+"");
//        for(int i = 0;i<users.size();i++){
//            Log.e("user",users.get(i).getUserName());
//        }


    }

    public void click(View v){
//        ObjectAnimator alpah = ObjectAnimator.ofFloat(mBinding.animButton,"alpha",100,0);
//        alpah.setDuration(2000);
//        alpah.setInterpolator(new DecelerateInterpolator());
//        alpah.setRepeatCount(-1);
//        alpah.setRepeatMode(ValueAnimator.REVERSE);
//        alpah.start();


        //左右拉伸动画
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mBinding.animButton, "scaleX", 1f, 0f);
//        scaleX.setDuration(3000);
//        scaleX.setRepeatCount(-1);
//        scaleX.setInterpolator(new DecelerateInterpolator());
//        scaleX.setRepeatMode(ValueAnimator.REVERSE);
//        scaleX.start();
//
//        //上下拉伸动画
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mBinding.animButton, "scaleY", 1f, 0f);
//        scaleY.setDuration(3000);
//        scaleY.setRepeatCount(-1);
//        scaleY.setInterpolator(new DecelerateInterpolator());
//        scaleY.setRepeatMode(ValueAnimator.REVERSE);
//        scaleY.start();
//
//        //平移动画,三四个参数是从什么坐标移动到什么坐标
////      ObjectAnimator translate = ObjectAnimator.ofFloat(iv, "x", iv.getX(), 0f);
//        ObjectAnimator translate = ObjectAnimator.ofFloat(mBinding.animButton, "y", mBinding.animButton.getY(), -20f);
//        translate.setDuration(3000);
//        translate.setRepeatCount(-1);
//        translate.setInterpolator(new DecelerateInterpolator());
//        translate.setRepeatMode(ValueAnimator.REVERSE);
//        translate.start();
//
//        //rotate 旋转动画,纵向旋转180度（上下翻转）
//        ObjectAnimator rotateX = ObjectAnimator.ofFloat(mBinding.animButton, "rotationX", 0f, 180f);
//        rotateX.setDuration(3000);
//        rotateX.setRepeatCount(-1);
//        rotateX.setInterpolator(new DecelerateInterpolator());
//        rotateX.setRepeatMode(ValueAnimator.REVERSE);
//        rotateX.start();
//
//        //rotate 旋转动画,横向旋转180度（左右翻转）
//        ObjectAnimator rotateY = ObjectAnimator.ofFloat(mBinding.animButton, "rotationY", 0f, 180f);
//        rotateY.setDuration(3000);
//        rotateY.setRepeatCount(-1);
//        rotateY.setInterpolator(new DecelerateInterpolator());
//        rotateY.setRepeatMode(ValueAnimator.REVERSE);
//        rotateY.start();
//
//        //改变背景颜色的动画
        ObjectAnimator backgroundColor = ObjectAnimator.ofInt(mBinding.animButton,
                "backgroundColor", Color.RED, Color.BLUE, Color.GRAY,
                Color.GREEN);
        backgroundColor.setDuration(3000);
        backgroundColor.setRepeatCount(-1);
        backgroundColor.setInterpolator(new DecelerateInterpolator());
        backgroundColor.setRepeatMode(ValueAnimator.REVERSE);
        /*
        * ArgbEvaluator：这种评估者可以用来执行类型之间的插值整数值代表ARGB颜色。
         * FloatEvaluator：这种评估者可以用来执行浮点值之间的插值。
         * IntEvaluator：这种评估者可以用来执行类型int值之间的插值。
         * RectEvaluator：这种评估者可以用来执行类型之间的插值矩形值。
         *
         * 由于本例是改变View的backgroundColor属性的背景颜色所以此处使用ArgbEvaluator
 */
        backgroundColor.setEvaluator(new ArgbEvaluator());
        backgroundColor.start();
    }
}
