package com.example.asus.replugindemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.component.service.PluginServiceClient;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.File;
import java.lang.reflect.Field;

public class HostActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        iv = findViewById(R.id.iv);
        tv=findViewById(R.id.tv);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                startRePlugin("com.example.asus.plugin", "mnt/shared/Other/plugin.apk");
                break;
            case R.id.btn1:
                //启动插件中的activity
                //方法一
                RePlugin.startActivity(HostActivity.this,
                        RePlugin.createIntent("com.example.asus.plugin", "com.example.asus.plugin.PluginActivity"));
//                //会崩溃，不能用这种方法
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName("com.example.asus.plugin", "com.example.asus.plugin.PluginActivity"));
//                startActivity(intent);
//                //会崩溃，不能用这种方法
//                Intent intent1 = RePlugin.createIntent("com.example.asus.plugin", "com.example.asus.plugin.PluginActivity");
//                startActivity(intent1);
                break;
            case R.id.btn2:
                PluginServiceClient.bindService(HostActivity.this, RePlugin.createIntent
                        ("com.example.asus.plugin", "com.example.asus.plugin.PluginService"), null, BIND_AUTO_CREATE);
                break;
            case R.id.btn3:
                Context context = RePlugin.fetchContext("com.example.asus.plugin");
                //获取插件中的图片资源
                Class<?> c=null;
                try {
                    c=context.getClassLoader().loadClass("com.example.asus.plugin.R$drawable");
                    int drawableId= (int) c.getField("ic_face_black_24dp").get(null);
                    iv.setImageDrawable(context.getResources().getDrawable(drawableId));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                //获取插件中的字符串资源
                Class<?> c1=null;
                try {
                    c1=context.getClassLoader().loadClass("com.example.asus.plugin.R$string");
                    Field field=c1.getField("app_name");
                    int strId= (int) field.get(null);
                    tv.setText(context.getResources().getString(strId));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    private void startRePlugin(String pluginName, String apkPath) {
        //安装插件过程
        PluginInfo pluginInfo = RePlugin.getPluginInfo(pluginName);
        //插件文件，只有存在就进行安装或者更新
        File file = new File(apkPath);
        //判断是否已经安装插件
        if (pluginInfo == null) {
            //插件未安装的情况
            if (!file.exists()) {
                Toast.makeText(HostActivity.this, "插件安装失败，插件文件不存在", Toast.LENGTH_SHORT).show();
            } else {
                //安装插件
                PluginInfo pluginInfo1 = RePlugin.install(apkPath);
                if (pluginInfo1 == null) {
                    Toast.makeText(HostActivity.this, "插件安装失败，安装出错", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HostActivity.this, "插件安装成功", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            //插件已安装,是否需要升级，判断条件是file是否为空
            if (file.exists()) {
                PluginInfo pluginInfo1 = RePlugin.install(file.getAbsolutePath());
                if (pluginInfo1 == null) {
                    Toast.makeText(HostActivity.this, "插件升级失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HostActivity.this, "插件升级成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(HostActivity.this, "插件已安装", Toast.LENGTH_SHORT).show();
                RePlugin.preload(pluginInfo);
            }
        }
    }

}
