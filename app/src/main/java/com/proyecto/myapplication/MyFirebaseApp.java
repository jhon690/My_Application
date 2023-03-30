package com.proyecto.myapplication;

import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseApp extends android.app.Application{
 // codigo que permite ejecutar app sin internet
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
