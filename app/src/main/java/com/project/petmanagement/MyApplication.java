package com.project.petmanagement;

import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.project.petmanagement.services.StorageService;

public class MyApplication extends Application {
    private static StorageService storageService;
    public static final String CHANNEL_ID = "notification_id";
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        storageService = new StorageService(getApplicationContext());
        createChannelNotification();
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.d("ddddd", token);
//
//                    }
//                });
    }
    private void createChannelNotification(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public static StorageService getStorageService(){
        return storageService;
    }
}
