package com.ys.baseproject;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ys.baseproject.databinding.ActivityThreadBinding;
import com.ys.baseproject.properties.CircleTransform;
import com.ys.baseproject.view.CircleImageDrawable;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by yunshan on 17/3/21.
 */

public class ThreadLocalActivity extends AppCompatActivity{

    static ActivityThreadBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thread);
//        setContentView(R.layout.activity_test);


        TextView textView = (TextView) findViewById(R.id.text);


        User user =new User("11","22");
        user.setUrl("https://imgsa.baidu.com/baike/s%3D220/sign=8ebf59a15243fbf2c12ca121807fca1e/fcfaaf51f3deb48fc4b7dd77f11f3a292cf578b8.jpg");

        mBinding.setUser(user);
        Glide.with(this).load(user.getUrl()).into(mBinding.imageview);

//        textView.setBackgroundDrawable(new WaterDrawable(this));
//        Dao<User, ?> dao = DbOpenHelper.getInstance(getApplicationContext()).getDao(User.class);
//
//        try {
//            dao.create(new User("222","22"));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
//        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//
//        View view = LayoutInflater.from(this).inflate(R.layout.fragment_layout,null);
//
//        getRootView(this).addView(view);
////        viewGroup.addView(view);
//
//        Log.e("info",viewGroup.getChildCount()+"");
    }

    public ViewGroup getRootView(Activity context){
        ViewGroup rootView = (ViewGroup) context.findViewById(android.R.id.content);

        return rootView;
    }

    @BindingAdapter(value = {"url","placeholder"},requireAll = false)
    public static void setImage(ImageView imageView ,String url,int placeHolder) {
        Glide.with(imageView.getContext()).load(url).transform(new CircleTransform(imageView.getContext()))
                .into(mBinding.imageview);
    }


    @BindingConversion
    public static Drawable getColorDrawable(String color){
        return new ColorDrawable(Color.parseColor(color));
    }
}
