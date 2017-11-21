package com.example.asus.replugindemo;

/**
 * Created by asus on 2017/11/16.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.qihoo360.replugin.RePluginCallbacks;

/**
 * 宿主针对RePlugin的自定义行为
 *
 */
public class HostCallbacks extends RePluginCallbacks {

    public HostCallbacks(Context context) {
        super(context);
    }

    @Override
    public boolean onLoadLargePluginForActivity(Context context, String plugin, Intent intent, int process) {
        return super.onLoadLargePluginForActivity(context, plugin, intent, process);
    }

    @Override
    public boolean onPluginNotExistsForActivity(final Context context, final String plugin, Intent intent, int process) {
        // FIXME 当插件"没有安装"时触发此逻辑，可打开您的"下载对话框"并开始下载。
        // FIXME 其中"intent"需传递到"对话框"内，这样可在下载完成后，打开这个插件的Activity
        if (BuildConfig.DEBUG) {
            Log.d("morse", "onPluginNotExistsForActivity: Start download... p=" + plugin + "; i=" + intent);
        }
        return super.onPluginNotExistsForActivity(context, plugin, intent, process);
    }
}