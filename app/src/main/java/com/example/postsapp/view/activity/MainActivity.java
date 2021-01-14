package com.example.postsapp.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.postsapp.R;
import com.example.postsapp.view.fragment.FlikerPostFragment;
import com.example.postsapp.view.fragment.WebViewFragment;

public class MainActivity extends SingleFragmentActivity implements
        FlikerPostFragment.FlikerPostFragmentCallback {
    public static Intent start(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public Fragment getFragment() {
        return FlikerPostFragment.newInstance();
    }

    @Override
    public void onStartWebView(String url) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container,
                        WebViewFragment.newInstance(url)).
                commit();
    }
}