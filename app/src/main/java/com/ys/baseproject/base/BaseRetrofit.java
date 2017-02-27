package com.ys.baseproject.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                        .build();
            }
            return retrofit;
        }
    }


    private static OkHttpClient getNewClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
