package com.example.abdroidservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.abdroidservices.bound_service.MusicBoundService;
import com.example.abdroidservices.intent_service.MusicIntentService;
import com.example.abdroidservices.service.MusicService;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStart;
    private Button mBtnStop;
    private Intent intent;
    public final String TAG = MainActivity.class.getSimpleName();
    private MusicBoundService musicBoundService;
    private boolean isServiceBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStart = findViewById(R.id.btnStart);
        mBtnStop = findViewById(R.id.btnStop);

//        intent = new Intent(MainActivity.this, MusicService.class);
//        intent = new Intent(MainActivity.this, MusicIntentService.class);

        intent = new Intent(MainActivity.this, MusicBoundService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(intent);
                if (isServiceBound){
                    musicBoundService.startMusic();
                }
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopService(intent);
                if (isServiceBound){
                    musicBoundService.pauseMusic();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy of Activity");
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBoundService.MusicServiceBinder musicServiceBinder = (MusicBoundService.MusicServiceBinder) service;
            musicBoundService = musicServiceBinder.getMusicService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };

}