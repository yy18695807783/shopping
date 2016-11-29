package com.example.lenovo.myshapping.app;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

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

        //分享初始化
        ShareSDK.initSDK(this);

        //激光推送
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
    }

    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }

}
