package com.bhagyashree.wipropocproject.retrofit;

import com.bhagyashree.wipropocproject.model.PlaceModel;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();

    private static ApiManager sApiManager;

    private ApiInterface apiInterface;

    private ApiManager() {
        apiInterface = ApiClient.getInstance().getClient().create(ApiInterface.class);
    }

    public static synchronized ApiManager getInstance() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }

        return sApiManager;
    }

    public void getListAPI(Callback<PlaceModel> callback) {
        Call<PlaceModel> tokenCall = apiInterface.getNewsList();
        tokenCall.enqueue(callback);
    }
}
