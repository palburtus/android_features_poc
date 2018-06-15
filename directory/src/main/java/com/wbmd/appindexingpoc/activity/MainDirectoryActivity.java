package com.wbmd.appindexingpoc.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.instantapps.InstantApps;
import com.google.gson.Gson;
import com.wbmd.appindexingpoc.adapter.DirectoryAdapter;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.directory.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.wbmd.appindexingpoc.network.ProfileService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainDirectoryActivity extends AppCompatActivity {
    private static final String TAG = MainDirectoryActivity.class.getSimpleName();
    private Button mSeeArticleButton;
    private DirectoryAdapter mAdapter;
    private List<Profile> mProfileList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_directory);

        setUpRecyclerView();
        setUpToolBar();
        setUpConversionButton();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainDirectoryActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new DirectoryAdapter(this, new ICallback() {
            @Override
            public void onItemClicked(Object o) {
                handleListItemClicked((Profile) o);
            }
        });
        recyclerView.setAdapter(mAdapter);
        getBaseballProfiles();
    }

    private void handleListItemClicked(Profile profile) {
        Intent i = new Intent(MainDirectoryActivity.this, ProfileDetailsActivity.class);
        i.putExtra(this.getString(R.string.profile), profile);
        startActivity(i);
    }

    private void setUpConversionButton() {
        mSeeArticleButton = findViewById(R.id.see_more_button);
            mSeeArticleButton.setVisibility(View.VISIBLE);
            mSeeArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstantApps.showInstallPrompt(MainDirectoryActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });
    }

    public Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.google_play_link)))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(false);
            bar.setDisplayShowTitleEnabled(true);
            bar.setTitle(R.string.player_roster_title);
        }
    }
    private void getBaseballProfiles() {
        ProfileService service =  ProfileService.getService();
        Call<List<Profile>> call = service.getBaseballProfiles();
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                    Log.e(TAG, response.message());
                    mAdapter.updateAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
