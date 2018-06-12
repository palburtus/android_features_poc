package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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


public class MainDirectoryActivity extends BaseActivity {
    private static final String TAG = MainDirectoryActivity.class.getSimpleName();
    private Button mSeeArticleButton;
    private DirectoryAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_directory);

        setUpToolBar();
        setUpRecyclerView();
        mAdapter.updateAdapter(getBaseballProfileList());
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
    }

    private void handleListItemClicked(Profile profile) {
        Intent i = new Intent(MainDirectoryActivity.this, ProfileDetailsActivity.class);
        i.putExtra(this.getString(R.string.profile), profile);
        startActivity(i);
    }

    private List<Profile> getBaseballProfileList() {
        List<Profile> list = new ArrayList<>();
        String json = null;
        try {
            InputStream is = MainDirectoryActivity.this.getAssets().open("BaseballProfiles.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Profile>>() {
        }.getType();
        list = gson.fromJson(json, type);

        for (Profile profileItem : list) {
//            profileItem = getDefaultBaseballProfile();
//            profileItem.setFullName(cleanString(profileItem.getFullName()));
            profileItem.setFullName(cleanString(profileItem.getFullName()));
            profileItem.setLogo("ny_logo");
            profileItem.setAddress(this.getString(R.string.default_address_top));
            profileItem.setAddressBottom(this.getString(R.string.default_address_bottom));
            profileItem.setSpecialty(this.getString(R.string.default_baseball_specialty));
            profileItem.setLocationPhoto("baseball_location");
            profileItem.setPhoto("placeholder");
        }
        return list;
    }

    private String cleanString(String str){
        String cleanString = str;
        if(str.contains("\\")) {
            int index = str.lastIndexOf('\\');
            cleanString = str.substring(0, index);
        }
        return cleanString;
    }

    private void setUpConversionButton() {
        mSeeArticleButton = findViewById(R.id.see_more_button);
        final Boolean isInstantApp = InstantApps.isInstantApp(this);
        if (isInstantApp) {
            mSeeArticleButton.setVisibility(View.VISIBLE);
            mSeeArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstantApps.showInstallPrompt(MainDirectoryActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });
        }
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
}
