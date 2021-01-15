package com.example.postsapp.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.postsapp.model.FlikerPost;
import com.example.postsapp.network.NetworkConstant;
import com.example.postsapp.repository.model.FlikerResponse;
import com.example.postsapp.repository.model.PhotoItem;
import com.example.postsapp.retrofit.FlikerRetrofit;
import com.example.postsapp.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlikerPagingDataSource extends PageKeyedDataSource<Integer, FlikerPost> {
    private FlikerRetrofit mFlikerRetrofit;

    {
        mFlikerRetrofit=
                RetrofitInstance.getRetrofitInstance().getRetrofit().create(FlikerRetrofit.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, FlikerPost> callback) {
        Call<FlikerResponse> flikerResponseCall=mFlikerRetrofit.getFlickerPostList(NetworkConstant.getMapQueryParameters(1));
            flikerResponseCall.enqueue(new Callback<FlikerResponse>() {
                @Override
                public void onResponse(Call<FlikerResponse> call, Response<FlikerResponse> response) {
                    if (response.body()!=null) {
                    List<FlikerPost> flikerPostList=getFlikerPost(response.body());
                        callback.onResult(flikerPostList,null,2);
                    }
                }

                @Override
                public void onFailure(Call<FlikerResponse> call, Throwable t) {

                }
            });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FlikerPost> callback) {
        Call<FlikerResponse> flikerResponseCall=mFlikerRetrofit.getFlickerPostList(NetworkConstant.getMapQueryParameters(1));
        flikerResponseCall.enqueue(new Callback<FlikerResponse>() {
            @Override
            public void onResponse(Call<FlikerResponse> call, Response<FlikerResponse> response) {
                if (response.body()!=null) {
                    List<FlikerPost> flikerPostList=getFlikerPost(response.body());
                    callback.onResult(flikerPostList,params.key-1);
                }
            }

            @Override
            public void onFailure(Call<FlikerResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FlikerPost> callback) {

        Call<FlikerResponse> flikerResponseCall=mFlikerRetrofit.getFlickerPostList(NetworkConstant.getMapQueryParameters(1));
        flikerResponseCall.enqueue(new Callback<FlikerResponse>() {
            @Override
            public void onResponse(Call<FlikerResponse> call, Response<FlikerResponse> response) {
                if (response.body()!=null) {
                    List<FlikerPost> flikerPostList=getFlikerPost(response.body());
                    callback.onResult(flikerPostList,params.key+1);
                }
            }

            @Override
            public void onFailure(Call<FlikerResponse> call, Throwable t) {

            }
        });
    }

    private List<FlikerPost> getFlikerPost(FlikerResponse flikerResponse) {
        List<FlikerPost> flikerPosts=new ArrayList<>();
        for (PhotoItem photoItem : flikerResponse.getPhotos().getPhoto()) {
            FlikerPost flikerPost=
                    new FlikerPost(photoItem.getTitle(),
                            photoItem.getUrlS(),
                            photoItem.getDateupload(),
                            photoItem.getId());

            flikerPosts.add(flikerPost);
        }

        return flikerPosts;
    }
}
