package com.ys.baseproject.base;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/2/27.
 */

public class BaseRetrofit {

    private static final long DEFAULT_TIMEOUT = 5000;
    private static Retrofit retrofit;
    private static final String BASEURL = "";

    public static Retrofit getRetrofit() {

        synchronized (BaseRetrofit.class) {
            if (retrofit == null) {
                 retrofit = new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .client(getNewClient())
                        .addConverterFactory(GsonConverterFactory.create())
                         //可以加上Rxjava 相关代码 配合retrofit的使用 2.2.2版本找不到RxJavaCallAdapterFactory
                        //http://blog.csdn.net/aiynmimi/article/details/53382567  这篇博客专门介绍Rxjava2
                         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }


    private static OkHttpClient getNewClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                //添加Log拦截器 此外还可以添加自定义拦截器
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    //如果我们每次网络请求都要上传一个标示Token
    class CustomInterCeptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            RequestBody body = RequestBody.create()
            request.newBuilder().post()
            return null;
        }
    }
}
