package com.wbmd.appindexingpoc;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by acaldwell on 5/25/18.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseApp.initializeApp(getApplicationContext());

//        if(!FirebaseApp.getApps(this).isEmpty()) {
//            FirebaseApp.initializeApp(this);
//        }
    }
}
