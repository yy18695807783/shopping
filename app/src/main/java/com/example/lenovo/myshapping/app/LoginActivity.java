package com.example.lenovo.myshapping.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_login_back)
    ImageButton ibLoginBack;
    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.ib_login_visible)
    ImageButton ibLoginVisible;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @Bind(R.id.ib_weibo)
    ImageButton ibWeibo;
    @Bind(R.id.ib_qq)
    ImageButton ibQq;
    @Bind(R.id.ib_wechat)
    ImageButton ibWechat;

    private int count;


    private void initFind() {
        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibWeibo.setOnClickListener(this);
        ibQq.setOnClickListener(this);
        ibWechat.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initFind();
    }


    @Override
    public void onClick(View v) {
        if (v == ibLoginBack) {
            finish();
        } else if (v == ibLoginVisible) {

            count++;
            if (count % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }
        } else if (v == btnLogin) {//登陆
            String userName = etLoginPhone.getText().toString().trim();
            String passWrold = etLoginPwd.getText().toString().trim();
            if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWrold)){
                Toast.makeText(LoginActivity.this, "密码或者帐号不能为空", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this, userName + "登陆成功", Toast.LENGTH_SHORT).show();
            }



        } else if (v == ibWeibo) {
            Toast.makeText(LoginActivity.this, "微博", Toast.LENGTH_SHORT).show();
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);

        } else if (v == ibQq) {
            Toast.makeText(LoginActivity.this, "QQ", Toast.LENGTH_SHORT).show();
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);

        } else if (v == ibWechat) {
            Toast.makeText(LoginActivity.this, "微信", Toast.LENGTH_SHORT).show();
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);

        }
    }

//    private UMAuthListener umAuthListener = new UMAuthListener() {
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
//            //
//            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListene1);
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
//        }
//    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

//    private UMAuthListener umAuthListene1 = new UMAuthListener() {
//
//        @Override
//        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//            //获取登录的名字
//            screen_name = map.get("screen_name");
//            //获取登录的图片
//            profile_image_url = map.get("profile_image_url");
//            Intent intent = getIntent();
//            intent.putExtra("screen_name", screen_name);
//            intent.putExtra("profile_image_url", profile_image_url);
//            setResult(1, intent);
//            finish();
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA share_media, int i) {
//
//        }
//    };

}
