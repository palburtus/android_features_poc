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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.squareup.picasso.Picasso;
import com.wbmd.appindexingpoc.adapter.ExtraAdapter;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.directory.R;
import com.wbmd.appindexingpoc.network.ProfileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileDetailsActivity extends AppCompatActivity {
    private static final String TAG = ProfileDetailsActivity.class.getSimpleName();
    Profile mProfile;
    private ExtraAdapter mAdapter;
    private List<String> mExtrasList = new ArrayList<>();
    private Button mSeeArticleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Intent i = getIntent();
        if(i.getParcelableExtra(getString(R.string.profile)) == null && i.getStringExtra("path") != null){
            String path = i.getStringExtra("path");
            mProfile = getProfileForPath(path);
        } else {
            mProfile = i.getParcelableExtra(getString(R.string.profile));
        }

        String[] myResArray = getResources().getStringArray(R.array.baseball_extras);
        mExtrasList.addAll(Arrays.asList(myResArray));

        setExtrasList();
        setUpConversionButton();
        loadUi();
        setUpToolBar();
    }

    private Profile getProfileForPath(String path){
        List<Profile> list = getBaseballProfiles();
        Profile profile = new Profile();
        for(Profile p:list){
            if(p.getLastName().equalsIgnoreCase(path)){
                profile = p;
            }
        }
        return profile;
    }

    private void loadUi() {
        TextView name = findViewById(R.id.profile_name);
        TextView specialty = findViewById(R.id.profile_specialty);
        TextView ratingNumber = findViewById(R.id.profile_ratings_number);
        ImageView profileImage = findViewById(R.id.profile_image);
        ImageView locationImage = findViewById(R.id.map);
        TextView addressTop = findViewById(R.id.profile_practice_address_top);
        TextView addressBottom = findViewById(R.id.profile_practice_address_bottom);

        Picasso.with(this)
                .load(mProfile.getPhoto())
                .into(profileImage);

        Picasso.with(this)
                .load(mProfile.getLocationPhoto())
                .into(locationImage);

        name.setText(mProfile.getFirstName());
        specialty.setText(mProfile.getSpecialty());
        addressTop.setText(mProfile.getAddress());
        addressBottom.setText(mProfile.getAddressBottom());
        ratingNumber.setText("0");
    }

    private void setExtrasList(){
        RecyclerView r = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProfileDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        r.setLayoutManager(linearLayoutManager);
        mAdapter = new ExtraAdapter(mExtrasList, this, new ICallback() {
            @Override
            public void onItemClicked(Object o) {
                Intent i = new Intent(ProfileDetailsActivity.this, ExtraItemActivity.class);
                i.putExtra("profile", mProfile);
                i.putExtra("extra", o.toString());
                startActivity(i);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.google_play_link)))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setUpConversionButton() {
        mSeeArticleButton = findViewById(R.id.see_more_button);
            mSeeArticleButton.setVisibility(View.VISIBLE);
            mSeeArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstantApps.showInstallPrompt(ProfileDetailsActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });
            }

    private List<Profile> getBaseballProfiles() {
        final List<Profile> list = new ArrayList<>();
        ProfileService service =  ProfileService.getService();
        Call<List<Profile>> call = service.getBaseballProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, response.message());
                    list.addAll(response.body());
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return list;
    }
}
