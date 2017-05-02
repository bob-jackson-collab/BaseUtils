package com.ys.baseproject.rx;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yunshan on 17/4/26.
 */

public class HeaderInterceptor implements Interceptor{

    private Headers headers;

    public HeaderInterceptor(Headers headers){
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request build = request.newBuilder().headers(headers).build();
        return chain.proceed(build);
    }
}
