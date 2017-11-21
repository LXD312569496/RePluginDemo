package com.example.asus.replugindemo;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;

/**
 * Created by asus on 2017/11/15.
 */

public class MyApplication extends RePluginApplication{

    @Override
    public void onCreate() {
        super.onCreate();

        RePlugin.App.onCreate();
    }

    @Override
    protected RePluginConfig createConfig() {
        RePluginConfig c = new RePluginConfig();
        // 允许“插件使用宿主类”。默认为“关闭”
        c.setUseHostClassIfNotFound(true);
        // FIXME RePlugin默认会对安装的外置插件进行签名校验，这里先关掉，避免调试时出现签名错误
        c.setVerifySign(false);
        c.setPrintDetailLog(BuildConfig.DEBUG);
        c.setUseHostClassIfNotFound(true);
        // 针对“安装失败”等情况来做进一步的事件处理
        c.setEventCallbacks(new HostEventCallbacks(this));
        c.setMoveFileWhenInstalling(true);
        // FIXME 若宿主为Release，则此处应加上您认为"合法"的插件的签名，例如，可以写上"宿主"自己的。
        // RePlugin.addCertSignature("AAAAAAAAA");

        return c;
    }
    @Override
    protected RePluginCallbacks createCallbacks() {
        return new HostCallbacks(this);
    }

}
