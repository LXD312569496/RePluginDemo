package com.example.asus.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by asus on 2017/11/17.
 * 多进程占坑
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv = findViewById(R.id.tv);
        tv.setText("之前进程id："+getIntent().getIntExtra("host",0)+
                "\n当前进程id：" + android.os.Process.myPid());

    }
}
