package com.example.lenovo.myshapping.utils;

import android.content.Context;
import android.view.View;

import com.example.lenovo.myshapping.app.MyAppliction;

/**
 * Created by 颜银 on 2016/11/12.
 * QQ:443098360
 * 微信：y443098360
 * 作用：专门提供为处理一些UI相关的问题而创建的工具类，提供资源获取的通用方法，避免每次都写重复的代码获取结果。
 */
public class UIUtils {

    //获得全局的Context
    public static Context getContext() {
        return MyAppliction.getContext();
    }

    //通过资源id获得相应的颜色
    public static int getColor(int colorID) {
        int color = getContext().getResources().getColor(colorID);
        return color;
    }

    //通过视图id获得加载视图
    public static View getView(int viewId) {
        View view = View.inflate(getContext(), viewId, null);
        return view;
    }

    //通过资源id获得字符串数组
    public static String[] getStringArr(int arrID) {
        String[] stringArray = getContext().getResources().getStringArray(arrID);
        return stringArray;
    }

    //屏幕相关
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);//四舍五入  获得px
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//四舍五入  获得dp
    }


}
