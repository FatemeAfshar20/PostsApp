package com.example.postsapp.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.postsapp.model.FlikerPost;

public class FlickerDataSourceFactory extends DataSource.Factory<Integer, FlikerPost>{
    MutableLiveData<PageKeyedDataSource<Integer,FlikerPost>> mPageKeyedDataSourceMutableLiveData=
            new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource<Integer, FlikerPost> create() {
        FlikerPagingDataSource flikerPagingDataSource=new FlikerPagingDataSource();

        mPageKeyedDataSourceMutableLiveData.postValue(flikerPagingDataSource);

        return flikerPagingDataSource;
    }

    public LiveData<PageKeyedDataSource<Integer, FlikerPost>> getPageKeyedDataSourceMutableLiveData() {
        return mPageKeyedDataSourceMutableLiveData;
    }
}
