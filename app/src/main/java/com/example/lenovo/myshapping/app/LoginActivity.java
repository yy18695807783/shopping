package com.example.lenovo.myshapping.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.user.utils.PreferenceUtils;
import com.example.lenovo.myshapping.utils.BitmapUtils;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

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
    @Bind(R.id.iv_login_icon)
    ImageView ivLoginIcon;

    private int count;
//    private int CAMERA = 100;//相机
//    private int PICTURE = 200;//图库

    //第三放登陆生明
    private Tencent mTencent;
    private UserInfo mInfo;


    private void initFind() {
        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibWeibo.setOnClickListener(this);
        ibQq.setOnClickListener(this);
        ibWechat.setOnClickListener(this);
        ivLoginIcon.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initFind();
        initData();

//        //如果在本地存储了用户头像，则优先从本地获取
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File externalFilesDir = this.getExternalFilesDir(null);
//            File file = new File(externalFilesDir, "icon.png");
//            if (file.exists()) {
//                //存在图片
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                //先执行压缩图片
//                Bitmap zoomBitmap = BitmapUtils.zoom(bitmap, UIUtils.dp2px(62), UIUtils.dp2px(62));
//                //设置圆形--剪裁圆形图片
//                Bitmap circleBitmap = BitmapUtils.circleBitmap(zoomBitmap);
//                ivLoginIcon.setImageBitmap(circleBitmap);
//
//            }
//        }

    }

    private void initData() {
        //QQ的初始化
        mTencent = Tencent.createInstance("1105704769", this.getApplicationContext());
        mInfo = new UserInfo(this, mTencent.getQQToken());

    }


    @Override
    public void onClick(View v) {
        if (v == ibLoginBack) {
            Intent intent = getIntent();
            intent.putExtra("userName", "");
            setResult(MyConstants.RESAULT_LOGIN, intent);
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
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWrold)) {
                Toast.makeText(LoginActivity.this, "密码或者帐号不能为空", Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = getIntent();
                intent.putExtra("userName", userName);
                setResult(MyConstants.RESAULT_LOGIN, intent);
                Toast.makeText(LoginActivity.this, userName + "登陆成功", Toast.LENGTH_SHORT).show();
                finish();
            }


        } else if (v == ibWeibo) {
            Toast.makeText(LoginActivity.this, "微博", Toast.LENGTH_SHORT).show();


        } else if (v == ibQq) {
//            Toast.makeText(LoginActivity.this, "QQ", Toast.LENGTH_SHORT).show();

            mTencent.login(this, "all", loginListener);


        } else if (v == ibWechat) {
            Toast.makeText(LoginActivity.this, "微信", Toast.LENGTH_SHORT).show();


        } else if (v == ivLoginIcon) {//登陆界面的用户头像
            Toast.makeText(LoginActivity.this, "更换图片方法已经注销了", Toast.LENGTH_SHORT).show();
            //改变他头像从本地
//            changeIcon();
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {

            initOpenidAndToken(values);

            //下面的这个必须放到这个地方，要不然就会出错
            updateUserInfo();

        }
    };

//    private void changeIcon() {
//        new AlertDialog.Builder(this)
//                .setTitle("选择路径")
//                .setItems(new String[]{"相机", "图库"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {//点击回调
//                        switch (which) {
//                            case 0://相机
//                                //打开系统拍照程序，选择拍照图片
//                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(camera, CAMERA);
//                                break;
//                            case 1://图库
//                                //打开系统图库程序，选择图片
//                                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                startActivityForResult(picture, PICTURE);
//                                break;
//                        }
//                    }
//                })
//                .setCancelable(true)
//                .show();
//    }
//
//
//
//    //将修改后的图片保存在本地存储中：storage/sdcard/Android/data/应用包名/files/xxx.png
//    private void saveImage(Bitmap circleBitmap) throws FileNotFoundException {
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File externalFilesDir = this.getExternalFilesDir(null);
//            File file = new File(externalFilesDir, "icon.png");
//            //将Bitmap持久化
//            circleBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
//        }
//    }
//
//
//    /**
//     * 根据系统相册选择的文件获取路径
//     *
//     * @param uri
//     */
//    @SuppressLint("NewApi")
//    private String getPath(Uri uri) {
//        int sdkVersion = Build.VERSION.SDK_INT;
//        //高于4.4.2的版本
//        if (sdkVersion >= 19) {
//            Log.e("TAG", "uri auth: " + uri.getAuthority());
//            if (isExternalStorageDocument(uri)) {
//                String docId = DocumentsContract.getDocumentId(uri);
//                String[] split = docId.split(":");
//                String type = split[0];
//                if ("primary".equalsIgnoreCase(type)) {
//                    return Environment.getExternalStorageDirectory() + "/" + split[1];
//                }
//            } else if (isDownloadsDocument(uri)) {
//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
//                        Long.valueOf(id));
//                return getDataColumn(this, contentUri, null, null);
//            } else if (isMediaDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//
//                Uri contentUri = null;
//                if ("image".equals(type)) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                } else if ("video".equals(type)) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                } else if ("audio".equals(type)) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                }
//                final String selection = "_id=?";
//                final String[] selectionArgs = new String[]{split[1]};
//                return getDataColumn(this, contentUri, selection, selectionArgs);
//            } else if (isMedia(uri)) {
//                String[] proj = {MediaStore.Images.Media.DATA};
//                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
//                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                actualimagecursor.moveToFirst();
//                return actualimagecursor.getString(actual_image_column_index);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // Return the remote address
//            if (isGooglePhotosUri(uri))
//                return uri.getLastPathSegment();
//            return getDataColumn(this, uri, null, null);
//        }
//        // File
//        else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            return uri.getPath();
//        }
//        return null;
//    }
//
//    /**
//     * uri路径查询字段
//     *
//     * @param context
//     * @param uri
//     * @param selection
//     * @param selectionArgs
//     * @return
//     */
//    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
//        Cursor cursor = null;
//        final String column = "_data";
//        final String[] projection = {column};
//        try {
//            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                final int index = cursor.getColumnIndexOrThrow(column);
//                return cursor.getString(index);
//            }
//        } finally {
//            if (cursor != null)
//                cursor.close();
//        }
//        return null;
//    }
//
//    private boolean isExternalStorageDocument(Uri uri) {
//        return "com.android.externalstorage.documents".equals(uri.getAuthority());
//    }
//
//    public static boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//    }
//
//    public static boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri.getAuthority());
//    }
//
//    public static boolean isMedia(Uri uri) {
//        return "media".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is Google Photos.
//     */
//    public static boolean isGooglePhotosUri(Uri uri) {
//        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
//    }


    //第三放登陆监听
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                return;
            }
            doComplete((JSONObject) response);
        }

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onCancel() {

        }

        protected void doComplete(JSONObject values) {

        }
    }


    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);

                }

                @Override
                public void onCancel() {

                }
            };

            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {

        }
    }

    private void setAvator(String url) {
        Picasso.with(LoginActivity.this).load(url).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap bitmap) {
                //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                Bitmap zoom = BitmapUtils.zoom(bitmap, 140, 140);
                //对请求回来的Bitmap进行圆形处理
                Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                bitmap.recycle();//必须队更改之前的进行回收
                return ciceBitMap;
            }

            @Override
            public String key() {
                return "";
            }
        }).into(ivLoginIcon);
    }


    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private String imgurl;//用户头像的url
    private String nickname;//昵称或登录账号
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            JSONObject response = (JSONObject) msg.obj;

            try {
                 Log.e("TAG","response==========" + response);
                imgurl = response.getString("figureurl_qq_2");
                nickname = response.getString("nickname");
                String password = "123456";

                //设置 用户名 密码
                setAvator(imgurl);
                etLoginPhone.setText(nickname);
                etLoginPwd.setText(password);
                //保存
                PreferenceUtils.putString(LoginActivity.this,
                        MyConstants.USER_NAME, nickname);
                PreferenceUtils.putString(LoginActivity.this,
                        MyConstants.USER_PASSWORD, "123456");
                PreferenceUtils.putString(LoginActivity.this,
                        MyConstants.IMAGE_URL,imgurl);

                //设置返回的结果
                Intent intent = getIntent();
                intent.putExtra("screen_name",nickname);
                intent.putExtra("profile_image_url",imgurl);
                setResult(RESULT_OK,intent);

                Log.e("TAG","nickname=========" + nickname);

                finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 点击回调，保存头像图片的方法
     * 以及第三方登录的回调方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


//        String path = getCacheDir() + "/tx.png";
//
//        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {//相机回调
//            //拍照
//            Bundle bundle = data.getExtras();
//            // 获取相机返回的数据，并转换为图片格式
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//
//            //先执行压缩图片
//            Bitmap zoomBitmap = BitmapUtils.zoom(bitmap, UIUtils.dp2px(62), UIUtils.dp2px(62));
//            //设置圆形--剪裁圆形图片
//            Bitmap circleBitmap = BitmapUtils.circleBitmap(zoomBitmap);
//
//            //设置图片   注意:真实项目中我们在这将图片保存到服务器中
//            ivLoginIcon.setImageBitmap(circleBitmap);
//
//            //保存到内存中
//            try {
//                saveImage(circleBitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
//        } else if (requestCode == PICTURE && resultCode == RESULT_OK && data != null) {//相册回调
//            Uri selectedImage = data.getData();
//
//            //这里返回的uri情况就有点多了
//            //**:在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file:
//            // ....在4.4.2返回的是content://com.android.providers.media.documents/document/image:3951或者
//            //总结：uri的组成，eg:content://com.example.project:200/folder/subfolder/etc
//            //content:--->"scheme"
//            //com.example.project:200-->"host":"port"--->"authority"[主机地址+端口(省略) =authority]
//            //folder/subfolder/etc-->"path" 路径部分
//            //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,所以要保证无论是哪个系统版本都能正确获取到图片资源的话
//            //就需要针对各种情况进行一个处理了
//            String pathResult = getPath(selectedImage);
//
//            if (!TextUtils.isEmpty(path)) {//路径存在-->文件存在
//
//                Bitmap bitmap = BitmapFactory.decodeFile(pathResult);
//                //先执行压缩图片
//                Bitmap zoomBitmap = BitmapUtils.zoom(bitmap, UIUtils.dp2px(62), UIUtils.dp2px(62));
//                //设置圆形--剪裁圆形图片
//                Bitmap circleBitmap = BitmapUtils.circleBitmap(zoomBitmap);
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(path);
//                    //保存到内存中 保存路径为  path
//                    circleBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                    //真实项目当中，是需要上传到服务器的..这步我们就不做了。
//                    ivLoginIcon.setImageBitmap(circleBitmap);
//
//                    //将图片保存在本地
//                    saveImage(circleBitmap);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }

        //第三放登陆方法

        Log.d("ruolan", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}
