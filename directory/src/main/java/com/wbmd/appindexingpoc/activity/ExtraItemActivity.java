package com.wbmd.appindexingpoc.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.wbmd.appindexingpoc.directory.R;
import com.wbmd.appindexingpoc.model.Profile;

/**
 * Created by acaldwell on 6/12/18.
 */

public class ExtraItemActivity extends BaseActivity {

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
            bar.setTitle(mProfile.getFullName());
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
                    InstantApps.showInstallPrompt(ExtraItemActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });

        }
    }
}
