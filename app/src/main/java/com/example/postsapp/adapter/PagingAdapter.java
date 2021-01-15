package com.example.postsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.postsapp.R;
import com.example.postsapp.databinding.ItemPostBinding;
import com.example.postsapp.model.FlikerPost;

public class PagingAdapter extends PagedListAdapter<FlikerPost, PagingAdapter.Holder> {
    private Context mContext;

    private static DiffUtil.ItemCallback<FlikerPost> sItemCallback=new DiffUtil.ItemCallback<FlikerPost>() {
        @Override
        public boolean areItemsTheSame(@NonNull FlikerPost oldItem, @NonNull FlikerPost newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FlikerPost oldItem, @NonNull FlikerPost newItem) {
            return oldItem.equals(newItem);
        }
    };

    public PagingAdapter(Context context) {
        super(sItemCallback);
        mContext=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding itemPostBinding=
                DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_post,parent,false);

        return new Holder(itemPostBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(getItem(position));
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
}
