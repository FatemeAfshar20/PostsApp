package com.example.postsapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.postsapp.model.FlikerPost;
import com.example.postsapp.network.NetworkConstant;
import com.example.postsapp.repository.FlickerDataSourceFactory;
import com.example.postsapp.repository.FlickerPostRepository;

public class FlikerPostViewModel extends AndroidViewModel {
    private LiveData<PageKeyedDataSource<Integer,FlikerPost>> mPageKeyedDataSourceLiveData;
    private LiveData<PagedList<FlikerPost>> mPagedListLiveData;


    public FlikerPostViewModel(@NonNull Application application) {
        super(application);
        FlickerDataSourceFactory flickerDataSourceFactory=new FlickerDataSourceFactory();

        mPageKeyedDataSourceLiveData=
                flickerDataSourceFactory.getPageKeyedDataSourceMutableLiveData();

        PagedList.Config pagedList=new PagedList.Config.Builder().
                setPageSize(Integer.parseInt(NetworkConstant.PER_PAGE)).build();

        mPagedListLiveData=
                new LivePagedListBuilder(flickerDataSourceFactory,pagedList).build();
    }

    public LiveData<PagedList<FlikerPost>> getPagedListLiveData() {
        return mPagedListLiveData;
    }
}
