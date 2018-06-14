package com.wbmd.appindexingpoc;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BaseApplication", "onCreate");
//        FirebaseApp.initializeApp(getApplicationContext());

        if(!FirebaseApp.getApps(this).isEmpty()) {
            Log.e("application", "firebase empty");
            FirebaseApp.initializeApp(this);
        }
    }
}