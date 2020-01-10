package com.bhagyashree.wipropocproject.retrofit;

import com.bhagyashree.wipropocproject.BuildConfig;
import com.bhagyashree.wipropocproject.constant.NetworkConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = BuildConfig.BASE_URL;

    private static ApiClient sApiClient;

    private Retrofit mRetrofit;

    private ApiClient() {
        mRetrofit = getClient();
    }

    public static ApiClient getInstance() {
        if (sApiClient == null) {
            synchronized (ApiClient.class) {
                sApiClient = new ApiClient();
            }
        }
        return sApiClient;
    }

    public Retrofit getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.connectTimeout(NetworkConstant.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(NetworkConstant.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NetworkConstant.WRITE_TIME_OUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();

        return mRetrofit;
    }
}
