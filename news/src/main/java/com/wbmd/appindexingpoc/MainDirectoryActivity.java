package com.wbmd.appindexingpoc;

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
import android.view.View;
import android.widget.Button;

import com.google.android.instantapps.InstantApps;
import com.google.gson.Gson;
import com.wbmd.appindexingpoc.adapter.DirectoryAdapter;
import com.wbmd.appindexingpoc.callback.ICallback;
import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.news.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class MainDirectoryActivity extends AppCompatActivity {
    private static final String TAG = MainDirectoryActivity.class.getSimpleName();
    private Button mSeeArticleButton;
    private DirectoryAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_directory);
        Log.e("news activity", "oncreate");
        setUpToolBar();

        Intent intent = getIntent();
        String action = intent.getAction();
        String data = intent.getDataString();
//            TextView text = findViewById(R.id.message);
//            text.setText(intent.getStringExtra("message"));
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
//                text.setText(intent.getStringExtra(String.valueOf(action)));
            // TODO: Parse the data URL and show right content for the URL.
        }
        setUpRecyclerView();
        mAdapter.updateAdapter(getProfileList());
        setUpConversionButton();
    }

    private void setUpRecyclerView() {
        Log.e("MainDirectory", "Setup recyclerview");
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
        i.putExtra("profile", profile);
        startActivity(i);
    }

    private List<Profile> getProfileList() {
        List<Profile> list = new ArrayList<>();
        String json = null;
        try {
            InputStream is = MainDirectoryActivity.this.getAssets().open("Profiles.json");
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
            profileItem.setLogo("ny_logo");
            profileItem.setAddress("395 Hudson St. NY, NY 10014");
            profileItem.setSpecialty("P");
            profileItem.setPhoto("placeholder");
        }
        return list;
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

    private Intent getPostInstallIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.wbmd.appindexingpoc"))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(false);
            bar.setDisplayShowTitleEnabled(true);
            bar.setTitle("Player Roster");
        }
    }
}

//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        return Actions.newView("News", "http://alburt.us/news.html");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        FirebaseUserActions.getInstance().start(getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        FirebaseUserActions.getInstance().end(getIndexApiAction());
//        super.onStop();
//    }


//        ProfileService service = ProfileService.getService();
//        Call<Teams> call = service.getTeams();
//        call.enqueue(new Callback<Teams>() {
//            @Override
//            public void onResponse(Call<Teams> call, Response<Teams> response) {
//                Teams teams = response.body();
//                Log.e(TAG, "onresponse" + " - " +response.body());
//
//                List<Player> players = teams.getPlayers();
//                mProfileList = new ArrayList<>();
//
//                for(Player p : players){
//                    Profile profile = new Profile();
//                    profile.setFirstName(p.getFirstName());
//                    profile.setLastName(p.getLastName());
//                    profile.setAddress(p.getBirthstate());
//                    profile.setSpecialty(p.getPrimaryPosition());
//                    mProfileList.add(profile);
//                }
//                mAdapter.updateAdapter(mProfileList);
//            }
//
//            @Override
//            public void onFailure(Call<Teams> call, Throwable t) {
//                Log.e(TAG, t.getMessage());
//                Log.e(TAG, t.getLocalizedMessage());
//                Log.e(TAG, call.toString());
//            }
//        });
//    }
