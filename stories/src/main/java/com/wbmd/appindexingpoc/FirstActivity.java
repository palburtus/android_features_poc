package com.wbmd.appindexingpoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;
import com.wbmd.appindexingpoc.stories.R;

/**
 * Created by palburtus on 10/10/17.
 */

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Intent intent = getIntent();
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            // TODO: Parse the data URL and show right content for the URL.
        }
    }
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        return Actions.newView("First", "http://alburt.us");
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
