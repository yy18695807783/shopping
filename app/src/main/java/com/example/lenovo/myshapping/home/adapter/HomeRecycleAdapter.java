package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.app.GoodsInfoActivity;
import com.example.lenovo.myshapping.home.actyvity.GoodsListActivity;
import com.example.lenovo.myshapping.home.bean.GoodsBean;
import com.example.lenovo.myshapping.home.bean.ResultBean;
import com.example.lenovo.myshapping.home.utils.AlphaPageTransformer;
import com.example.lenovo.myshapping.home.utils.ScaleInTransformer;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：分类型RecycleView的类
 */
public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String GOODS_BEAN = "goods_bean";
    //上下文
    private Context mContext;
    //数据对象
    private ResultBean mResaultBean;

    //定义类型
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    //当前类型--默认第一种
    public int currentType = BANNER;
    private final LayoutInflater mLayoutInflater;

    public HomeRecycleAdapter(Context context, ResultBean resaultBean) {
        mContext = context;
        mResaultBean = resaultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    /**
     * 得到类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER://横幅广告
                currentType = BANNER;
                break;
            case CHANNEL://频道
                currentType = CHANNEL;
                break;
            case ACT://活动
                currentType = ACT;
                break;
            case SECKILL: //秒杀
                currentType = SECKILL;
                break;
            case RECOMMEND://推存
                currentType = RECOMMEND;
                break;
            case HOT://热卖
                currentType = HOT;
                break;
        }
        return currentType;

    }

    //总共有多少个item
    @Override
    public int getItemCount() {
        //开始1-->6变话  有几个返回几。逐渐增大
        return 6;
    }


    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }


    /**
     * 绑定数据的方法，相当于getView方法
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResaultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResaultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResaultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResaultBean.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResaultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResaultBean.getHot_info());
        }

    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_more_hot)
        TextView tvMoreHot;
        @Bind(R.id.gv_hot)
        GridView gvHot;

        private Context mContext;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = context;
        }

        public void setData(final List<ResultBean.HotInfoBean> hot_info) {

            HotAdapter adapter = new HotAdapter(mContext, hot_info);
            gvHot.setAdapter(adapter);


            //点击事件
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String cover_price = hot_info.get(position).getCover_price();
                    String name = hot_info.get(position).getName();
                    String figure = hot_info.get(position).getFigure();
                    String product_id = hot_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);

                    //启动商品页面
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @Bind(R.id.gv_recommend)
        GridView gvRecommend;

        private Context mContext;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = context;
        }

        public void setData(final List<ResultBean.RecommendInfoBean> recommend_info) {
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(adapter);


            //点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String cover_price = recommend_info.get(position).getCover_price();
                    String name = recommend_info.get(position).getName();
                    String figure = recommend_info.get(position).getFigure();
                    String product_id = recommend_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                    //启动商品页面
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    //设置秒杀倒计时
    private boolean isFirst = true;

    //秒杀类中的布局组件---此处不能用绑定的啦 只能用FindViewById的方法初始化
    TextView tvTimeSeckill;
    private int dt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    dt = dt - 1000;
                    SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                    tvTimeSeckill.setText(sd.format(new Date(dt)));

                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, 1000);
                    if (dt == 0) {
                        handler.removeMessages(0);
                    }
                    break;
            }
        }
    };

    class SeckillViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @Bind(R.id.rv_seckill)
        RecyclerView rvSeckill;

        private Context mContext;

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvTimeSeckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            this.mContext = context;
        }

        public void setData(final ResultBean.SeckillInfoBean seckill_info) {

            //设置时间
            if (isFirst) {
                dt = (int) (Integer.parseInt(seckill_info.getEnd_time()) - System.currentTimeMillis());
                isFirst = false;
            }

            SeckillAdapter adapter = new SeckillAdapter(mContext, seckill_info);
            rvSeckill.setAdapter(adapter);

            //设施RecycleView的管理器---横向  一排
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            //设置倒计时
            handler.sendEmptyMessageDelayed(0, 1000);


            //设置点击事件
            adapter.setOnSeckillRecyclerView(new SeckillAdapter.OnSeckillRecyclerView() {
                @Override
                public void onClick(int position) {

                    ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);
                    String name = listBean.getName();
                    String cover_price = listBean.getCover_price();
                    String figure = listBean.getFigure();
                    String product_id = listBean.getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                    //启动商品页面
                    startGoodsInfoActivity(goodsBean);
                }
            });

        }
    }


    class ActViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.act_viewpager)
        ViewPager actViewpager;

        private Context mContext;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = context;
        }


        public void setData(List<ResultBean.ActInfoBean> act_info) {

            //Viewpager设置参数
            actViewpager.setPageMargin(20);
            actViewpager.setOffscreenPageLimit(3);//>=3  理解：设置最大的图片数量
            //setPageTransformer 决定动画效果
            actViewpager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));

            actViewpager.setAdapter(new ActAdapter(mContext, act_info));

            //点击事件
            actViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.gv_channel)
        GridView gvChannel;
        private Context mContext;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = context;

        }

        public void setData(List<ResultBean.ChannelInfoBean> channel_info) {
            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));


            //点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position <= 8) {//只有显示的前9个（0 - 8）点击才穿过去信息
                        Intent intent = new Intent(mContext, GoodsListActivity.class);
                        intent.putExtra("position", position);
                        mContext.startActivity(intent);
                    } else {

                    }

                }
            });

        }
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.banner)
        Banner banner;

        private Context mContext;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = context;
        }


        public void setData(final List<ResultBean.BannerInfoBean> banner_info) {


            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //获取图片路径集合
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = MyConstants.BASE_URL_IMAGE + banner_info.get(i).getImage();
                Log.e("TAG", "图片url集合" + imageUrl);
                imageUrls.add(imageUrl);
            }


            banner.setImages(imageUrls);
            //设置banner动画效果   Transformer.CubeOut //立体效果
            banner.setBannerAnimation(Transformer.FlipVertical);
            //设置标题集合（当banner样式有显示title时）
            String[] titles = new String[]{"尚硅谷在线课堂", "抱歉，没座位了", "史上最高大上的开学典礼"};
            banner.setBannerTitles(Arrays.asList(titles));
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.RIGHT);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            Log.e("TAG", "3333333333333333-------" + imageUrls.size());


            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {

                    if(position - 1 < banner_info.size()){
                        int option = banner_info.get(position - 1).getOption();
                        String product_id = "";
                        String name = "";
                        String cover_price = "";
                        if (position - 1 == 0) {
                            product_id = "627";
                            cover_price = "32.00";
                            name = "剑三T恤批发";
                        } else if (position - 1 == 1) {
                            product_id = "21";
                            cover_price = "8.00";
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                        } else {
                            product_id = "1341";
                            cover_price = "50.00";
                            name = "【蓝诺】《天下吾双》 剑网3同人本";
                        }
                        String image = banner_info.get(position - 1).getImage();
                        GoodsBean goodsBean = new GoodsBean(name, cover_price, image, product_id);

                        //启动商品页面
                        startGoodsInfoActivity(goodsBean);
                    }




                }
            });

        }
    }

    /**
     * 启动商品详情页面
     */
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, goodsBean);
        mContext.startActivity(intent);
    }

    /**
     * banner加载图片用的
     */
    public class GlideImageLoader implements ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
            Log.e("TAG", "444444444444");

        }
    }


}
