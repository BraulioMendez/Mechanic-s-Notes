package com.brauliomendez.mechanicsnotes.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Braulio Méndez Jiménez
 * @since 28/06/16
 */
public class NotesApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
