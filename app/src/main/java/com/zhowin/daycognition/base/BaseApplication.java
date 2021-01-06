package com.zhowin.daycognition.base;

import android.app.Application;

/**
 * author : zho
 * date  ：2021/1/6
 * desc ：
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
