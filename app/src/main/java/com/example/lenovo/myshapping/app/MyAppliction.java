package com.example.lenovo.myshapping.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 颜银 on 2016/11/21.
 * QQ:443098360
 * 微信：y443098360
 * 作用：
 */
public class MyAppliction extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }

    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }

}
