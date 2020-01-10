package com.bhagyashree.wipropocproject.retrofit;

import android.content.Context;

public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();

    private static ApiManager sApiManager;

    private ApiInterface apiInterface;

    private ApiManager(Context context) {
        apiInterface = ApiClient.getInstance(context).getClient().create(ApiInterface.class);
    }

    public static synchronized ApiManager getInstance(Context context) {
        if (sApiManager == null) {
            sApiManager = new ApiManager(context);
        }

        return sApiManager;
    }
}
