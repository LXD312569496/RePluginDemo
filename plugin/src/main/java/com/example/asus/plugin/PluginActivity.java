package com.example.asus.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qihoo360.replugin.RePlugin;

import java.lang.reflect.Field;

public class PluginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.asus.replugindemo",
                        "com.example.asus.replugindemo.HostActivity"));
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent1 = new Intent();
                intent1.setComponent(new ComponentName("com.example.asus.replugindemo",
                        "com.example.asus.replugindemo.HostService"));
                startService(intent1);
                break;
            case R.id.btn3:
                //获取宿主中的字符串资源
                Class<?> clazz = null;
                try {
                    clazz = RePlugin.getHostClassLoader().loadClass("com.example.asus.replugindemo.R$string");
                    Field field = clazz.getField("app_name");
                    int identifierID = (int) field.get(null);
                    tv.setText(RePlugin.getHostContext().getResources().getString(identifierID));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                //获取宿主中的图片资源
                Class<?> c = null;
                try {
                    c = RePlugin.getHostClassLoader().loadClass("com.example.asus.replugindemo.R$drawable");
                    Field field = c.getField("ic_tag_faces_black_24dp");
                    int drawableId = (int) field.get(null);
                    Drawable drawable = RePlugin.getHostContext().getResources().getDrawable(drawableId);
                    iv.setImageDrawable(drawable);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


////              TODO:?????????获取不到资源
//                Context context = RePlugin.getHostContext();
//                final int drawableId = context.getResources().getIdentifier("ic_tag_faces_black_24dp", "drawable", context.getPackageName());
//                final Drawable drawable = context.getResources().getDrawable(drawableId);
////                iv.setImageResource(drawableId);
//                iv.setImageDrawable(drawable);
//                break;

//                int stringId=context.getResources().getIdentifier("app_name","string","com.example.asus.replugindemo");
//                tv.setText(stringId);
        }
    }
}
