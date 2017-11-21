package com.example.asus.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by asus on 2017/11/17.
 */

public class PluginService extends Service{

    private static final String TAG = "test";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: PluginService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:PluginService ");
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: PluginService");
        Toast.makeText(this,"服务已经开启",Toast.LENGTH_SHORT).show();
        return null;
    }
}
