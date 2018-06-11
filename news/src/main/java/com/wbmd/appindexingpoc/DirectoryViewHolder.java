package com.wbmd.appindexingpoc;


import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;

class DirectoryViewHolder extends RecyclerView.ViewHolder{
    private TextView mNameText;
    private ImageView mProfileImage;
    private TextView mPositionText;
    private TextView mAddressText;
    private ImageView mLogoImage;

    public DirectoryViewHolder(View itemView) {
        super(itemView);

        mNameText = itemView.findViewById(R.id.name_text);
        mPositionText = itemView.findViewById(R.id.position_text);
        mAddressText = itemView.findViewById(R.id.address_text);
        mProfileImage = itemView.findViewById(R.id.image_header);
        mLogoImage = itemView.findViewById(R.id.logo_image);
    }

    public void onBind(Profile profile, View.OnClickListener listener){
        mNameText.setText(profile.getFirstName());
        mPositionText.setText(profile.getSpecialty());
        mAddressText.setText(profile.getAddress());

        Picasso.with(itemView.getContext())
                .load(profile.getPhoto())
                .error(R.drawable.placeholder)
                .into(mProfileImage);

        Picasso.with(itemView.getContext())
                .load(profile.getLogo())
                .error(R.drawable.placeholder)
                .into(mLogoImage);

        itemView.setOnClickListener(listener);
    }
}
