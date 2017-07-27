package com.ys.baseproject.rx;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ys.baseproject.BuildConfig;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yunshan on 17/4/26.
 */

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, "");
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NotNullListTypeAdapterFactory())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(getOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.API_BASEURL)
                .build();

        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getOKHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        Headers.Builder header = new Headers.Builder();
        //可以在这添加头部信息
//        header.add("accept","false");
        builder.addInterceptor(new HeaderInterceptor(header.build()));

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor());
        }



        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            };

            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return builder.build();
    }


    private static class NotNullListTypeAdapterFactory implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> adapter = gson.getDelegateAdapter(this, type);
            final Class<? super T> rawType = type.getRawType();
            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    adapter.write(out, value);
                }

                @Override
                public T read(JsonReader in) throws IOException {

                    if (rawType == new TypeToken<List>() {
                    }.getRawType()) {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return (T) new ArrayList<>();
                        }
                    }
                    return adapter.read(in);
                }
            };
        }
    }
}
