package com.bhagyashree.wipropocproject.retrofit;

import com.bhagyashree.wipropocproject.model.PlaceModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("facts.json")
    Call<PlaceModel> getNewsList();
}
