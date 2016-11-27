package com.example.lenovo.myshapping.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.home.adapter.HomeRecycleAdapter;
import com.example.lenovo.myshapping.home.bean.ResultBean;
import com.example.lenovo.myshapping.user.activity.MessageCenterActivity;
import com.example.lenovo.myshapping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：主页fragment
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.tv_title_message)
    TextView tvTitleMessage;
    @Bind(R.id.rv_home)
    RecyclerView rvHome;
    @Bind(R.id.ib_top)
    ImageButton ibTop;
    @Bind(R.id.tv_title_search)
    TextView tvTitleSearch;
    //集合数据
    private ResultBean resaultBean;
    private HomeRecycleAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        //联网下载数据
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                    .get()
                    .url(Constants.HOME_URL)
                    .id(100)//下面回调的id
                    .build()
                    .execute(new MyStringCallback());
            }


            public class MyStringCallback extends StringCallback {


                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG", "联网失败" + e.toString());
                }

                @Override
                public void onResponse(String response, int id) {
                    //联网成功会到这里
                    Log.e("TAG", "联网成功Constants.HOME_URL = " + Constants.HOME_URL);

                    switch (id) {
                case 100:

                    //解析数据
                    processData(response);

                    //设置适配器
                    if (resaultBean != null && resaultBean.size() >= 0) {
                        adapter = new HomeRecycleAdapter(mContext, resaultBean);
                        rvHome.setAdapter(adapter);
                        Log.e("TAG", "222222222222222222---" + rvHome);
                    }


                    //设置recycleView的管理器
                    GridLayoutManager gridLayoutManager =  new GridLayoutManager(mContext, 1,GridLayoutManager.VERTICAL, false);

                    //设置滑动到哪个位置了的监听
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position <= 3) {//当分类型RecycleView屏幕条数小于3条或等于3条时，就隐藏。
                                ibTop.setVisibility(View.GONE);
                            } else {//完全显示第四条就开始显示ibTop 键
                                ibTop.setVisibility(View.VISIBLE);
                            }
                            return 1;//表示一行就占一个部分的GridLayoutManager分列
                        }
                    });

                    rvHome.setLayoutManager(gridLayoutManager);

                    break;
            }

        }
    }

    /**
     * 解析JSON数据
     *
     * @param json
     */
    private void processData(String json) {
        if (TextUtils.isEmpty(json)) {
            Toast.makeText(mContext, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        } else {//数据不为空
            JSONObject jsonObject = JSON.parseObject(json);
            //解析得到状态码
            String code = jsonObject.getString("code");//200
            String msg = jsonObject.getString("msg");//请求成功
            String result = jsonObject.getString("result");//需要解析的集合

            //得到resultBean的数据
            ResultBean bean = JSON.parseObject(result, ResultBean.class);
            String banner_info = bean.getString("banner_info");
            String act_info = bean.getString("act_info");
            String channel_info = bean.getString("channel_info");
            String hot_info = bean.getString("hot_info");
            String recommend_info = bean.getString("recommend_info");
            String seckill_info = bean.getString("seckill_info");

            resaultBean = new ResultBean();

            //设置BannerInfoBean数据
            List<ResultBean.BannerInfoBean> bannerInfoBeans = JSON.parseArray(banner_info, ResultBean.BannerInfoBean.class);
            resaultBean.setBanner_info(bannerInfoBeans);
            String value = jsonObject.getString("value");
            ResultBean.BannerInfoBean.ValueBean valueBean = JSON.parseObject(value, ResultBean.BannerInfoBean.ValueBean.class);

            //设置actInfoBeans数据
            List<ResultBean.ActInfoBean> actInfoBeans = JSON.parseArray(act_info, ResultBean.ActInfoBean.class);
            resaultBean.setAct_info(actInfoBeans);

            //设置channelInfoBeans的数据
            List<ResultBean.ChannelInfoBean> channelInfoBeans = JSON.parseArray(channel_info, ResultBean.ChannelInfoBean.class);
            resaultBean.setChannel_info(channelInfoBeans);
//            JSONObject value1 = JSON.parseObject("value");
//            JSON.parseObject(value1, ResultBean.ChannelInfoBean.ValueBean.class);

            //设置hotInfoBeans的数据
            List<ResultBean.HotInfoBean> hotInfoBeans = JSON.parseArray(hot_info, ResultBean.HotInfoBean.class);
            resaultBean.setHot_info(hotInfoBeans);

            //设置recommendInfoBeans的数据
            List<ResultBean.RecommendInfoBean> recommendInfoBeans = JSON.parseArray(recommend_info, ResultBean.RecommendInfoBean.class);
            resaultBean.setRecommend_info(recommendInfoBeans);

            //设置seckillInfoBean的数据
            ResultBean.SeckillInfoBean seckillInfoBean = JSON.parseObject(seckill_info, ResultBean.SeckillInfoBean.class);
            resaultBean.setSeckill_info(seckillInfoBean);


        }
//
    }


    @Override
    protected void initListener() {

        //置顶监听
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "置顶", Toast.LENGTH_SHORT).show();
                rvHome.scrollToPosition(0);
            }
        });

        //消息监听
        tvTitleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MessageCenterActivity.class));
            }
        });

        //搜索监听
        tvTitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

