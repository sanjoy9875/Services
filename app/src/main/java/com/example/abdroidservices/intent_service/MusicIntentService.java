package com.example.abdroidservices.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.abdroidservices.R;
import com.example.abdroidservices.service.MusicService;

public class MusicIntentService extends IntentService {

    public final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    public MusicIntentService() {
        super("MusicIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
        mediaPlayer = MediaPlayer.create(this, R.raw.tisket_a_tasket);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
      mediaPlayer.start();
        Log.d(TAG,"onHandleIntent");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onHandleIntent");
    }
}