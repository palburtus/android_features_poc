package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wbmd.appindexingpoc.model.Profile;
import com.wbmd.appindexingpoc.network.ProfileService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutingActivity extends AppCompatActivity {
    private static final String TAG = RoutingActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();

        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
            String lastPath = appLinkData.getLastPathSegment();

            Log.e("lastpath", lastPath);
            if(lastPath.equalsIgnoreCase("directory.html")){
                Intent i = new Intent(RoutingActivity.this, MainDirectoryActivity.class);
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valilind.html")){
                Intent i = new Intent(RoutingActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valilind");
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valililind.html")){
                Intent i = new Intent(RoutingActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valililind");
                startActivity(i);
                finish();
            } else if (lastPath.equalsIgnoreCase("valilililind.html")){
                Intent i = new Intent(RoutingActivity.this, ProfileDetailsActivity.class);
                i.putExtra("path", "valilililind");
                startActivity(i);
                finish();
            }
        }
    }
    }



