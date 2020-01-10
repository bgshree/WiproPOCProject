package com.bhagyashree.wipropocproject.retrofit;

import com.bhagyashree.wipropocproject.model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("facts.json")
    Call<NewsModel> getNewsList();
}
