package com.wbmd.appindexingpoc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.wbmd.appindexingpoc.directory.R;
import com.wbmd.appindexingpoc.model.Profile;


public class ExtraItemActivity extends AppCompatActivity {

    private String mExtraItem = "";
    private Profile mProfile = new Profile();
    private Button mSeeArticleButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item);

        mExtraItem = getIntent().getStringExtra("extra");
        mProfile = getIntent().getParcelableExtra("profile");

        setUpToolBar();
        setUpUi();
        setUpConversionButton();
    }

    private void setUpUi() {
        TextView titleText = findViewById(R.id.title_text);
        titleText.setText(mExtraItem);
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setTitle(mProfile.getFirstName());
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
                    InstantApps.showInstallPrompt(ExtraItemActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });
            }
}
