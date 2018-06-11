package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.wbmd.appindexingpoc.adapter.ExtraAdapter;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acaldwell on 6/11/18.
 */

public class ProfileDetailsActivity extends AppCompatActivity {

    Profile mProfile;
    private ExtraAdapter mAdapter;
    private List<String> mExtrasList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Intent i = getIntent();
        mProfile = i.getParcelableExtra("profile");
        mExtrasList.add("extra1");
        mExtrasList.add("extra2");
        mExtrasList.add("extra3");
        setUpToolBar();
        loadUi();
        setExtrasList();
    }

    private void loadUi() {
        TextView name = findViewById(R.id.profile_name);
        TextView specialty = findViewById(R.id.profile_specialty);
        TextView ratingNumber = findViewById(R.id.profile_ratings_number);
        ImageView profileImage = findViewById(R.id.profile_image);

        profileImage.setImageDrawable(getDrawable(R.drawable.ny_logo));
        name.setText(mProfile.getFirstName());
        specialty.setText(mProfile.getSpecialty());
        ratingNumber.setText("0");
    }

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
            bar.setTitle("Player Profile");
        }
    }

    private Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.wbmd.appindexingpoc"))
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
}
