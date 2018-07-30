package com.codelab.helmi.simrs.fcm;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Aplikasi extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
