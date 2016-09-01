package com.san.harsimar.firebasetest;

import android.app.Application;

import com.firebase.client.Firebase;

public class FirebaseTest extends Application {
    @Override
    public void onCreate() {
        Firebase.setAndroidContext(this);

        super.onCreate();
    }
}
