package com.example.abdroidservices.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.abdroidservices.R;

public class MusicService extends Service {

    public final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showNotificationAndStartForeGround();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.tisket_a_tasket);
        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        startMusic();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startMusic() {
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        Log.d(TAG,"onDestroy");
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void showNotificationAndStartForeGround() {
        String NOTIFICATION_CHANNEL_ID = "lloyd";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setContentText("Hey music is playing")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }
}