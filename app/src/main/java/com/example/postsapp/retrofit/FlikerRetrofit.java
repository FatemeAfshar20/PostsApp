package com.example.postsapp.retrofit;

import com.example.postsapp.model.FlikerPost;
import com.example.postsapp.repository.model.FlikerResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FlikerRetrofit {

    @GET(".")
    Call<FlikerResponse> getFlickerPostList(@QueryMap Map<String,String> queryMap);
}
