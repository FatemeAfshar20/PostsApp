package com.example.postsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.postsapp.R;
import com.example.postsapp.databinding.ItemPostBinding;
import com.example.postsapp.model.FlikerPost;

import java.util.ArrayList;
import java.util.List;

public class FlickerPageAdapter extends RecyclerView.Adapter<FlickerPageAdapter.Holder>{
    private List<FlikerPost> mFlikerPosts=new ArrayList<>();
    private Context mContext;
    private ItemPostBinding mBinding;
    private FlickerPageAdapterCallback mCallback;

    public void setCallback(FlickerPageAdapterCallback callback) {
        mCallback = callback;
    }

    public FlickerPageAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_post, parent,
                false);
        return new Holder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        FlikerPost flikerPost=mFlikerPosts.get(position);
            holder.bind(flikerPost);
            mBinding.btnSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW,
                            Uri.parse(flikerPost.getUrlSmall()));
                    mContext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mFlikerPosts.size();
    }

    public void setFlikerPosts(List<FlikerPost> flikerPosts) {
        mFlikerPosts = flikerPosts;
    }

    class Holder extends RecyclerView.ViewHolder {
        private ItemPostBinding mBinding;

        public Holder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(FlikerPost flikerPost){
            Glide.with(mContext).
                    load(flikerPost.getUrlSmall()).
                    centerCrop().
                    placeholder(R.drawable.ic_placeholder).
                    into(mBinding.imgPost);
            mBinding.setFlikerPost(flikerPost);
        }
    }

    public interface FlickerPageAdapterCallback{
        void startWebView(String url);
    }
}
