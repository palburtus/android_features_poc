package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.wbmd.appindexingpoc.adapter.ExtraAdapter;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfileDetailsActivity extends AppCompatActivity {

    Profile mProfile;
    private ExtraAdapter mAdapter;
    private List<String> mExtrasList = new ArrayList<>();
    private Button mSeeArticleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Intent i = getIntent();
        mProfile = i.getParcelableExtra(getString(R.string.profile));

        String[] myResArray = getResources().getStringArray(R.array.baseball_extras);
        mExtrasList.addAll(Arrays.asList(myResArray));

        setExtrasList();
        setUpConversionButton();
        loadUi();
        setUpToolBar();
    }

    private void loadUi() {
        TextView name = findViewById(R.id.profile_name);
        TextView specialty = findViewById(R.id.profile_specialty);
        TextView ratingNumber = findViewById(R.id.profile_ratings_number);
        ImageView profileImage = findViewById(R.id.profile_image);
        ImageView locationImage = findViewById(R.id.map);
        TextView addressTop = findViewById(R.id.profile_practice_address_top);
        TextView addressBottom = findViewById(R.id.profile_practice_address_bottom);

        profileImage.setImageDrawable(getDrawable(R.drawable.ny_logo));
        locationImage.setImageDrawable(getDrawable(R.drawable.baseball_location));
        name.setText(mProfile.getFullName());
        specialty.setText(mProfile.getSpecialty());
        addressTop.setText(mProfile.getAddress());
        addressBottom.setText(mProfile.getAddressBottom());
        ratingNumber.setText("0");
    }

//    private Drawable getDrawableResource(String photoRef){
//        Resources resources = this.getResources();
//        int resourceId = resources.getIdentifier(photoRef, "drawable", this.getPackageName());
//        return this.getDrawable(resourceId);
//    }

    private void setExtrasList(){
        RecyclerView r = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProfileDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        r.setLayoutManager(linearLayoutManager);
        mAdapter = new ExtraAdapter(mExtrasList, this, new ICallback() {
            @Override
            public void onItemClicked(Object o) {
                InstantApps.showInstallPrompt(ProfileDetailsActivity.this, getPostInstallIntent(), 0, "instant");
            }
        });
        r.setAdapter(mAdapter);
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setTitle(R.string.player_profile_title);
        }
    }

    private Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.google_play_link)))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setUpConversionButton() {
        mSeeArticleButton = findViewById(R.id.see_more_button);
        final Boolean isInstantApp = InstantApps.isInstantApp(this);
        if (isInstantApp) {
            mSeeArticleButton.setVisibility(View.VISIBLE);
            mSeeArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstantApps.showInstallPrompt(ProfileDetailsActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });

        }
    }
}
