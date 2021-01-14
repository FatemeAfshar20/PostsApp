package com.example.postsapp.retrofit;

import com.example.postsapp.network.NetworkConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit sRetrofit;
    private static RetrofitInstance sInstance;

    private RetrofitInstance() {
            sRetrofit=new Retrofit.Builder().
                    baseUrl(NetworkConstant.BASE_URL).
                     addConverterFactory(GsonConverterFactory.create()).
                    build();
    }

    public static RetrofitInstance getRetrofitInstance() {
        if (sInstance==null)
            sInstance=new RetrofitInstance();
        return sInstance;
    }

    public  Retrofit getRetrofit(){
        return sRetrofit;
    }
}
