package com.wbmd.appindexingpoc.viewholder;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;

public class DirectoryViewHolder extends RecyclerView.ViewHolder{
    private TextView mNameText;
    private ImageView mProfileImage;
    private TextView mSpecialty;
    private TextView mAddressTextTop;
    private TextView mAddressTextBottom;

    public DirectoryViewHolder(View itemView) {
        super(itemView);

        mProfileImage = itemView.findViewById(R.id.profile_image);
        mNameText = itemView.findViewById(R.id.name_text);
        mSpecialty = itemView.findViewById(R.id.specialty_text);
        mAddressTextTop = itemView.findViewById(R.id.address_top_text);
        mAddressTextBottom = itemView.findViewById(R.id.address_bottom_text);
    }

    public void onBind(Profile profile, View.OnClickListener listener){
        mNameText.setText(profile.getFullName());
        mSpecialty.setText(profile.getSpecialty());
        mAddressTextTop.setText(profile.getAddress());
        mAddressTextBottom.setText(profile.getAddressBottom());

        Picasso.with(itemView.getContext())
                .load(profile.getPhoto())
                .error(R.drawable.ny_logo)
                .into(mProfileImage);

        itemView.setOnClickListener(listener);
    }
}
