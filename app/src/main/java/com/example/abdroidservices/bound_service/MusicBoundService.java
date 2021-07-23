package com.example.abdroidservices.bound_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.abdroidservices.R;
import com.example.abdroidservices.service.MusicService;

public class MusicBoundService extends Service {

    private static final String TAG = MusicBoundService.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    public MusicBoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.tisket_a_tasket);
        Log.d(TAG,"onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
       return new MusicServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    public class MusicServiceBinder extends Binder {
        public MusicBoundService getMusicService(){
            return MusicBoundService.this;
        }
    }

    public void startMusic(){
        mediaPlayer.start();
    }

    public void pauseMusic(){
        mediaPlayer.pause();
    }
}