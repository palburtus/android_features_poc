package com.wbmd.appindexingpoc;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by palburtus on 3/13/18.
 */

public class PocApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
