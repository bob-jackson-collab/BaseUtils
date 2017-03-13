package com.ys.baseproject.base;

import com.ys.baseproject.BuildConfig;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yunshan on 17/3/13.
 *
 */

public class ServiceGenator {

    private static ServiceGenator intance;

    private static Object object = new Object();
    private Retrofit.Builder mBuilder;

    public static ServiceGenator getInstance(){

        synchronized (object){
            if(intance == null){
                intance = new ServiceGenator();
            }
        }
        return intance;
    }

    private ServiceGenator(){
        mBuilder.baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient());
    }

    public static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //添加log拦截器  也可以自定义拦截器 添加头部信息 、、证书等等相关信息  根据 api来定  具体参考http://blog.csdn.net/yang_song_song/article/details/61918168
        builder.addInterceptor(new HttpLoggingInterceptor());
        return builder.build();
    }

    private Retrofit getRetrofit(){
        return mBuilder.build();
    }

    public static <S> S createClass(Class<S> clazz){
        Retrofit retrofit;
        retrofit = getInstance().getRetrofit();
        return retrofit.create(clazz);
    }

}
