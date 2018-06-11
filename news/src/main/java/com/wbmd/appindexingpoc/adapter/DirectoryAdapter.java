package com.wbmd.appindexingpoc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wbmd.appindexingpoc.viewholder.*;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;

import java.util.ArrayList;
import java.util.List;


public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryViewHolder>{
    List<Profile> mProfileList = new ArrayList<>();
    Context mContext;
    ICallback mListener;

    public DirectoryAdapter(Context context, ICallback listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public DirectoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_list, parent, false);
        return new DirectoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DirectoryViewHolder holder, int position) {
        Profile p = mProfileList.get(holder.getAdapterPosition());
        holder.onBind(p, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(mProfileList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {

        if(mProfileList != null && mProfileList.size() > 1){
            return mProfileList.size();
        } else {
            return 0;
        }
    }

    public void updateAdapter(List<Profile> list){
        Log.e("updateAdapter", String.valueOf(list.size()));
        mProfileList.clear();
        mProfileList = list;
        notifyDataSetChanged();
    }
}

