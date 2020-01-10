package com.bhagyashree.wipropocproject.retrofit;

import android.content.Context;
import android.content.pm.PackageManager;

import com.bhagyashree.wipropocproject.BuildConfig;
import com.bhagyashree.wipropocproject.constant.NetworkConstant;
import com.bhagyashree.wipropocproject.utils.AppUtil;
import com.bhagyashree.wipropocproject.utils.DateTimeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = BuildConfig.BASE_URL;

    private static ApiClient sApiClient;

    private Retrofit mRetrofit;

    private String mDeviceId;

    private String mPackageId;

    private String mPackageHash;

    private ApiClient(Context context) {
        mRetrofit = getClient();
        try {
            mDeviceId = AppUtil.getAndroidId(context);
            mPackageId = AppUtil.getPackageId(context);
            mPackageHash = AppUtil.getPackageHash(context);
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static ApiClient getInstance(Context context) {
        if (sApiClient == null) {
            synchronized (ApiClient.class) {
                sApiClient = new ApiClient(context);
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

        client.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header(NetworkConstant.HEADER_KEY_CONTENT_TYPE, NetworkConstant.CONTENT_TYPE_APPLICATION_JSON)
                    .header(NetworkConstant.HEADER_KEY_PACKAGE_ID, mPackageId)
                    .header(NetworkConstant.HEADER_KEY_PACKAGE_HASH, mPackageHash)
                    .header(NetworkConstant.HEADER_KEY_DEVICE_ID, mDeviceId)
                    .header(NetworkConstant.HEADER_KEY_CLIENT_TIME,
                            DateTimeUtil.getTimeInHeaderFormat(System.currentTimeMillis()))
                    .build();

            return chain.proceed(request);
        });

        client.addInterceptor(loggingInterceptor);

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
