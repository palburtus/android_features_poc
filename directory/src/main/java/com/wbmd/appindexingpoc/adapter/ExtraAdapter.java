package com.wbmd.appindexingpoc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.directory.R;
import com.wbmd.appindexingpoc.viewholder.ExtraViewHolder;

import java.util.List;


public class ExtraAdapter extends RecyclerView.Adapter<ExtraViewHolder> {
    private List<String> mExtras;
    private Context mContext;
    private ICallback mListener;

    public ExtraAdapter(List<String> mExtras, Context mContext, ICallback listener) {
        this.mExtras = mExtras;
        this.mContext = mContext;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ExtraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_extra, parent, false);
        return new ExtraViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExtraViewHolder holder, int position) {
        String extra = mExtras.get(holder.getAdapterPosition());
        holder.onBind(extra, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(mExtras.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mExtras != null && mExtras.size() > 0){
            return mExtras.size();
        } else {
            return 0;
        }
    }
}
