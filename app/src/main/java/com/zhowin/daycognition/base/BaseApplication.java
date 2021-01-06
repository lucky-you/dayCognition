package com.zhowin.daycognition.base;

import android.app.Application;

import com.zhowin.dbdao.DBManager;

/**
 * author : zho
 * date  ：2021/1/6
 * desc ：
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DBManager.initDao();
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
