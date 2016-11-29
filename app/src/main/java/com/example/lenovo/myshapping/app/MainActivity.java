package com.example.lenovo.myshapping.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.find.fragment.FindFragment;
import com.example.lenovo.myshapping.home.fragment.HomeFragment;
import com.example.lenovo.myshapping.shoppingcar.fragment.ShoppingCarFragment;
import com.example.lenovo.myshapping.type.fragment.TypeFragment;
import com.example.lenovo.myshapping.user.fdragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private FragmentTransaction transaction;

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private TypeFragment typeFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private UserFragment userFragment;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);



//        Window window = this.getWindow();
//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        window.setStatusBarColor(getResources().getColor(R.color.red_light));
//
//        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
//            ViewCompat.setFitsSystemWindows(mChildView, true);
//        }

//        Window window = this.getWindow();
//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        window.setStatusBarColor(Color.parseColor("#ff0000"));
//
//        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
//            ViewCompat.setFitsSystemWindows(mChildView, true);
//        }


        initListener();
    }

    //初始化监听
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_home://主页
                        setSelect(0);
                        break;
                    case R.id.rb_main_type://分类
                        setSelect(1);
                        break;
                    case R.id.rb_main_find://发现
                        setSelect(2);
                        break;
                    case R.id.rb_main_car://购物车
                        setSelect(3);
                        break;
                    case R.id.rb_main_user://用户信息
                        setSelect(4);
                        break;
                }
            }
        });
        //默认选择首页
        rgMain.check(R.id.rb_main_home);
    }

    private void setSelect(int i) {

        //开启事务----开启一次提交一次
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        //先隐藏所有fragment显示
        hintFragment();
        if (i == 0) {//主页
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                transaction.add(R.id.fl_main, homeFragment);
            }
            transaction.show(homeFragment);
        } else if (i == 1) {//分类
            if (typeFragment == null) {
                typeFragment = new TypeFragment();
                transaction.add(R.id.fl_main, typeFragment);
            }
            transaction.show(typeFragment);
        } else if (i == 2) {//发现
            if (findFragment == null) {
                findFragment = new FindFragment();
                transaction.add(R.id.fl_main, findFragment);
            }
            transaction.show(findFragment);

        } else if (i == 3) {//购物车

            if (shoppingCarFragment == null) {
                shoppingCarFragment = new ShoppingCarFragment();
                transaction.add(R.id.fl_main, shoppingCarFragment);
            }

            if (shoppingCarFragment != null) {
                if (shoppingCarFragment.adapter != null) {
                    shoppingCarFragment.showData();//此方法在fragment的onCreat中只调用一次,在这调用刷新
                    Log.e("TAG", "刷新没？？？---------");
                    shoppingCarFragment.adapter.notifyDataSetChanged();
                }
            }

            transaction.show(shoppingCarFragment);

        } else if (i == 4) {//用户信息
            if (userFragment == null) {
                userFragment = new UserFragment();
                transaction.add(R.id.fl_main, userFragment);
            }
            transaction.show(userFragment);
        }
        transaction.commit();//别忘记提交
    }

    private void hintFragment() {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (typeFragment != null) {
            transaction.hide(typeFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (shoppingCarFragment != null) {
            transaction.hide(shoppingCarFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    /**
     * 实现2s内点击两次退出键才退出
     *
     * @param keyCode
     * @param event
     * @return
     */

    private Handler handler = new Handler();
    private boolean isOut = true;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isOut) {
            Toast.makeText(MainActivity.this, "在点一次就退出", Toast.LENGTH_SHORT).show();
            isOut = false;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isOut = true;//2s后初始化isOut为原来状态
                }
            }, 2000);

            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        JPushInterface.onPause(this);
    }

}
