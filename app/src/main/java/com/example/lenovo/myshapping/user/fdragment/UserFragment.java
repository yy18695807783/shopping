package com.example.lenovo.myshapping.user.fdragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.app.LoginActivity;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;

/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：用户中心
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_all_order)
    TextView tvAllOrder;
    @Bind(R.id.tv_user_pay)
    TextView tvUserPay;
    @Bind(R.id.tv_user_receive)
    TextView tvUserReceive;
    @Bind(R.id.tv_user_finish)
    TextView tvUserFinish;
    @Bind(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @Bind(R.id.tv_user_location)
    TextView tvUserLocation;
    @Bind(R.id.tv_user_collect)
    TextView tvUserCollect;
    @Bind(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @Bind(R.id.tv_user_score)
    TextView tvUserScore;
    @Bind(R.id.tv_user_prize)
    TextView tvUserPrize;
    @Bind(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @Bind(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @Bind(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @Bind(R.id.tv_user_feedback)
    TextView tvUserFeedback;



//    @Bind(R.id.scrollview)
//    ScrollView scrollview;
//    @Bind(R.id.tv_usercenter)
//    TextView tvUsercenter;
//    @Bind(R.id.ib_user_setting)
//    ImageButton ibUserSetting;
//    @Bind(R.id.ib_user_message)
//    ImageButton ibUserMessage;


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.scrollview)
    NestedScrollView scrollview;



    private String screen_name;
    private String profile_image_url;

    private void initFind() {
        ibUserIconAvator.setOnClickListener(this);
//        ibUserSetting.setOnClickListener(this);
//        ibUserMessage.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        if (v == ibUserIconAvator) {//头像登陆按钮
            startActivity(new Intent(getActivity(), LoginActivity.class));
//            Intent intent = new Intent(mContext, LoginActivity.class);
//            startActivityForResult(intent, 0);

        }
//        else if (v == ibUserSetting) {//设置按钮
//            Toast.makeText(mContext, "设置按钮", Toast.LENGTH_SHORT).show();
//
//        } else if (v == ibUserMessage) {//消息按钮
////            Toast.makeText(mContext, "消息按钮", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), MessageCenterActivity.class));
//
//        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user1;
    }

    @Override
    protected void initData() {
        initFind();
        //标题透明
//        tvUsercenter.setAlpha(0);
//
//        scrollview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int[] location = new int[2];
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE://下滑是正，上滑是负
//                        ibUserIconAvator.getLocationOnScreen(location);//初始状态为125,即最大值是125，全部显示不透明是（40？）
//                        float i = (location[1] - 40) / 85f;
//                        tvUsercenter.setAlpha(1 - i);//划动title就出现红色背景
//                        break;
//                }
//                return false;
//            }
//        });


//        toolbar.setAlpha(0);
//        toolbar.setTitleTextColor(Color.TRANSPARENT);
//        scrollview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int[] location = new int[2];
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE://下滑是正，上滑是负
//                        ibUserIconAvator.getLocationOnScreen(location);//初始状态为125,即最大值是125，全部显示不透明是（40？）
//                        float i = (location[1] - 40) / 85f;
//                        toolbar.setAlpha(1 - i);//划动title就出现红色背景
//                        toolbar.setTitleTextColor(Color.RED);
//                        break;
//                }
//                return false;
//            }
//        });

    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            String screen_name = data.getStringExtra("screen_name");
            String profile_image_url = data.getStringExtra("profile_image_url");

            Picasso.with(mContext).load(profile_image_url).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap bitmap) {
                    //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                    Bitmap zoom = BitmapUtils.zoom(bitmap, 90, 90);
                    //对请求回来的Bitmap进行圆形处理
                    Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                    bitmap.recycle();//必须队更改之前的进行回收
                    return ciceBitMap;
                }

                @Override
                public String key() {
                    return "";
                }
            }).into(ibUserIconAvator);

            tvUsername.setText(screen_name);
        }
    }

}
