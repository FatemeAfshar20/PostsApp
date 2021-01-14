package com.example.postsapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class PostApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            "postAppNotificationChannel",
                            "Post App Channel",
                            NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(
                    "this channel for showing notification when receiving new image");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
