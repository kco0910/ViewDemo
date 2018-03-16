package com.fly.viewdemo;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by Fj on 2018/3/16.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        setupLogger();
    }
    private void setupLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .tag("ViewDemo").build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
