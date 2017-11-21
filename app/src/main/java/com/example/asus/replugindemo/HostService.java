package com.example.asus.replugindemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by asus on 2017/11/17.
 */

public class HostService extends Service {

    private static final String TAG = "test";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: HostService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: HostService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: HostService");
        return null;
    }
}
