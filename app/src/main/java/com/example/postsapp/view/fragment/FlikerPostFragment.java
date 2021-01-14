package com.example.postsapp.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.postsapp.R;
import com.example.postsapp.adapter.FlickerPageAdapter;
import com.example.postsapp.databinding.FragmentFlikerPostBinding;
import com.example.postsapp.model.FlikerPost;
import com.example.postsapp.repository.FlickerPostRepository;
import com.example.postsapp.viewModel.FlikerPostViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlikerPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlikerPostFragment extends Fragment {

    private FragmentFlikerPostBinding mBinding;
    private FlikerPostViewModel mViewModel;
    private FlickerPageAdapter mAdapter;
    private FlickerPostRepository mRepository;

    private FlikerPostFragmentCallback mCallback;

    public FlikerPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FlikerPostFragmentCallback)
            mCallback = (FlikerPostFragmentCallback) context;
        else
            throw new ClassCastException(
                    "Must be implement FlikerPostFragmentCallback interface");
    }

    public static FlikerPostFragment newInstance() {
        FlikerPostFragment fragment = new FlikerPostFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new FlickerPostRepository();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<FlikerPost> flikerPosts = mRepository.fetchItems();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(flikerPosts);
                    }
                });
            }
        }).start();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_fliker_post,
                container,
                false);
        setupAdapter();
        return mBinding.getRoot();
    }

    private void setupAdapter() {
        mAdapter = new FlickerPageAdapter(getContext());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

        mAdapter.setCallback(new FlickerPageAdapter.FlickerPageAdapterCallback() {
            @Override
            public void startWebView(String url) {
                mCallback.onStartWebView(url);
            }
        });
    }

    public void updateUI(List<FlikerPost> flikerPostList) {
        if (mAdapter != null) {
            mAdapter.setFlikerPosts(flikerPostList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public interface FlikerPostFragmentCallback {
        void onStartWebView(String url);
    }
}