package com.example.postsapp.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postsapp.R;
import com.example.postsapp.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {
    public static final String ARG_WEB_URL = "Web Url";
    private FragmentWebViewBinding mBinding;
    private String mWebUrl;

    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString(ARG_WEB_URL,url);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebUrl=getArguments().getString(ARG_WEB_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_web_view,
                container,
                false);

        mBinding.webView.loadUrl(mWebUrl);

        return mBinding.getRoot();
    }

}