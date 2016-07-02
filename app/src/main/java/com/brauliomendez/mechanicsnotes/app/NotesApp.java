package com.brauliomendez.mechanicsnotes.app;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Braulio on 28/06/2016.
 */
public class NotesApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
