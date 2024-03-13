package com.project.petmanagement;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.project.petmanagement.services.StorageService;

public class MyApplication extends Application {
    private static StorageService storageService;
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        storageService = new StorageService(getApplicationContext());
    }
    public static StorageService getStorageService(){
        return storageService;
    }
}
