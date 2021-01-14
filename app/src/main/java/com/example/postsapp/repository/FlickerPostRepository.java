package com.example.postsapp.repository;

import com.example.postsapp.model.FlikerPost;
import com.example.postsapp.network.NetworkConstant;
import com.example.postsapp.repository.model.FlikerResponse;
import com.example.postsapp.repository.model.PhotoItem;
import com.example.postsapp.retrofit.FlikerRetrofit;
import com.example.postsapp.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FlickerPostRepository {
    public static final String TAG = "PostsApp";
    private FlikerRetrofit mFlikerRetrofit;
    private List<FlikerPost> mFlikerPosts=new ArrayList<>();

    public FlickerPostRepository() {
        mFlikerRetrofit = RetrofitInstance.getRetrofitInstance().getRetrofit().create(FlikerRetrofit.class);
    }

    public List<FlikerPost> fetchItems() {
        List<FlikerPost> flikerPosts=new ArrayList<>();
        Call<FlikerResponse> flikerResponseCall =
                mFlikerRetrofit.getFlickerPostList(NetworkConstant.QUERY_MAP);
        try {
            Response<FlikerResponse> response=flikerResponseCall.execute();
            FlikerResponse flikerResponse=response.body();

            for (PhotoItem photoItem : flikerResponse.getPhotos().getPhoto()) {
                FlikerPost flikerPost=
                        new FlikerPost(photoItem.getTitle(),
                                photoItem.getUrlS(),
                                photoItem.getDateupload(),
                                photoItem.getId());

                flikerPosts.add(flikerPost);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return flikerPosts;
    }

    public List<FlikerPost> getFlikerPosts() {
        return mFlikerPosts;
    }

    public void setFlikerPosts(List<FlikerPost> flikerPosts) {
        mFlikerPosts = flikerPosts;
    }
}
