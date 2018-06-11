package com.wbmd.appindexingpoc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wbmd.appindexingpoc.news.R;

/**
 * Created by acaldwell on 6/11/18.
 */

public class ExtraViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;

    public ExtraViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.title_text);
    }

    public void onBind(String extra, View.OnClickListener listener) {
        mTitle.setText(extra);
        itemView.setOnClickListener(listener);
    }
}
