package com.example.asus.replugindemo;

import android.content.Context;
import android.util.Log;

import com.qihoo360.replugin.RePluginEventCallbacks;
import com.qihoo360.replugin.model.PluginInfo;

/**
 * Created by asus on 2017/11/16.
 */

public class HostEventCallbacks extends RePluginEventCallbacks {
    public HostEventCallbacks(Context context) {
        super(context);
    }

    @Override
    public void onInstallPluginSucceed(PluginInfo info) {
        Log.d("morse", "onInstallPluginSucceed: Failed! info=" + info);
        super.onInstallPluginSucceed(info);
    }

    @Override
    public void onInstallPluginFailed(String path, InstallResult code) {
        // FIXME 当插件安装失败时触发此逻辑。您可以在此处做“打点统计”，也可以针对安装失败情况做“特殊处理”
        // 大部分可以通过RePlugin.install的返回值来判断是否成功
        Log.d("morse", "onInstallPluginFailed: Failed! path=" + path + "; r=" + code);
        super.onInstallPluginFailed(path, code);
    }

    @Override
    public void onStartActivityCompleted(String plugin, String activity, boolean result) {
        // FIXME 当打开Activity成功时触发此逻辑，可在这里做一些APM、打点统计等相关工作
        Log.d("morse", "onStartActivityCompleted: plugin=" + plugin + "\r\n result=" + result);
        super.onStartActivityCompleted(plugin, activity, result);
    }
}