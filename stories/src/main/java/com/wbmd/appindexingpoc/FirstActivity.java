package com.wbmd.appindexingpoc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.instantapps.InstantApps;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;
import com.wbmd.appindexingpoc.stories.R;

/**
 * Created by palburtus on 10/10/17.
 */

public class FirstActivity extends AppCompatActivity {

    private Button mSeeArticleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.e("first activity", "oncreate");

        Intent intent = getIntent();
        String action = intent.getAction();
        String data = intent.getDataString();
        TextView text = findViewById(R.id.message);
        text.setText(intent.getStringExtra("message"));

        mSeeArticleButton = findViewById(R.id.see_article_button);
        final Boolean isInstantApp = InstantApps.isInstantApp(this);
        if(isInstantApp) {
            mSeeArticleButton.setVisibility(View.VISIBLE);
            mSeeArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstantApps.showInstallPrompt(FirstActivity.this, getPostInstallIntent(), 0, "instant");
                }
            });

        } else {
            mSeeArticleButton.setVisibility(View.GONE);
            if (Intent.ACTION_VIEW.equals(action) && data != null) {
                text.setText(intent.getStringExtra(String.valueOf(action)));

                // TODO: Parse the data URL and show right content for the URL.
            }
        }
    }

    private Intent getPostInstallIntent(){
        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.wbmd.appindexingpoc"))
                .addCategory(Intent.CATEGORY_BROWSABLE);
    }
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        return Actions.newView("First", "http://alburt.us/default.html");
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
}
