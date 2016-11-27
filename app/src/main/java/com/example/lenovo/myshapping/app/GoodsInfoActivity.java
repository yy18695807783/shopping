package com.example.lenovo.myshapping.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.home.bean.GoodsBean;
import com.example.lenovo.myshapping.home.utils.VirtualkeyboardHeight;
import com.example.lenovo.myshapping.shoppingcar.activity.ShoppingCartActivity;
import com.example.lenovo.myshapping.shoppingcar.utils.CartProvider;
import com.example.lenovo.myshapping.shoppingcar.view.NumberAddSubView;
import com.example.lenovo.myshapping.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 颜银 on 2016/11/20.
 * QQ:443098360
 * 微信：y443098360
 * 作用：
 */
public class GoodsInfoActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @Bind(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @Bind(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @Bind(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @Bind(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @Bind(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @Bind(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @Bind(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_search)
    TextView tvMoreSearch;
    @Bind(R.id.tv_more_home)
    TextView tvMoreHome;
    @Bind(R.id.btn_more)
    Button btnMore;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.activity_goods_info)
    LinearLayout activityGoodsInfo;

    /**
     * 商城首页点击的商品详情信息
     */
    private GoodsBean goods_bean;
    private CartProvider cartProvider;


    private void initFinds() {

        ibGoodInfoBack.setOnClickListener(this);//顶部--返回键
        ibGoodInfoMore.setOnClickListener(this);//顶部--更多键

        tvMoreShare.setOnClickListener(this);//更多键--分享键
        tvMoreSearch.setOnClickListener(this);//更多键--搜索键
        tvMoreHome.setOnClickListener(this);//更多键--home键


        tvGoodInfoCallcenter.setOnClickListener(this);//底部--客服键
        tvGoodInfoCollection.setOnClickListener(this);//底部--收藏键
        tvGoodInfoCart.setOnClickListener(this);//底部--购物车键
        btnGoodInfoAddcart.setOnClickListener(this);//底部--加入购物车键
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {//顶部--返回键
            finish();
        } else if (v == ibGoodInfoMore) {//顶部--更多键
//            Toast.makeText(GoodsInfoActivity.this, "更多", Toast.LENGTH_SHORT).show();
            if (llRoot.getVisibility() == View.VISIBLE) {
                //隐藏
                llRoot.setVisibility(View.GONE);
            } else {
                //显示
                llRoot.setVisibility(View.VISIBLE);
            }
        } else if (v == tvGoodInfoCallcenter) {//底部--客服键
//            Toast.makeText(GoodsInfoActivity.this, "客服", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,CallCenterActivity.class));

        } else if (v == tvGoodInfoCollection) {//底部--收藏键
            Toast.makeText(GoodsInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();

        } else if (v == tvGoodInfoCart) {//底部--购物车键
//            Toast.makeText(GoodsInfoActivity.this, "到购物车界面", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(GoodsInfoActivity.this, ShoppingCartActivity.class));

        } else if (v == btnGoodInfoAddcart) {//底部--加入购物车键
//            Toast.makeText(GoodsInfoActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
            showPopwindow();

        }else if (v == tvMoreShare) {//更多键--分享键
            Toast.makeText(GoodsInfoActivity.this, "更多键--商品详情--分享键", Toast.LENGTH_SHORT).show();

        }else if (v == tvMoreSearch) {//更多键--搜索键
            Toast.makeText(GoodsInfoActivity.this, "更多键--商品详情--搜索键", Toast.LENGTH_SHORT).show();

        }else if (v == tvMoreHome) {//更多键--home键
            Toast.makeText(GoodsInfoActivity.this, "更多键--商品详情--home键", Toast.LENGTH_SHORT).show();

        }

    }

    //点击加入购物车的商品
    private void showPopwindow() {
        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        //方式二  View渲染
//        View view = View.inflate(this,R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);

        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = (NumberAddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Picasso.with(GoodsInfoActivity.this).load(Constants.BASE_URL_IMAGE + goods_bean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goods_bean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goods_bean.getCover_price());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(5);
        nas_goodinfo_num.setValue(goods_bean.getNumber());

        nas_goodinfo_num.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                int number = goods_bean.getNumber();
                goods_bean.setNumber(number + 1);
                if (number + 1 >= 5) {
                    Toast.makeText(GoodsInfoActivity.this, "该商品最多购买5个", Toast.LENGTH_SHORT).show();
                    goods_bean.setNumber(5);
                }
            }

            @Override
            public void subNumner(View view, int value) {
                int number = goods_bean.getNumber();
                goods_bean.setNumber(number - 1);

                if (number <= 1) {
                    Toast.makeText(GoodsInfoActivity.this, "该商品最少购买1个", Toast.LENGTH_SHORT).show();
                    goods_bean.setNumber(1);
                }
            }
        });

        //PopupWindow取消按钮
        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        //PopupWindow确定按钮------------------------------------
        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //商品选择的数量===========================
                int number = goods_bean.getNumber();

                //PopupWindow窗口消失
                window.dismiss();

                //添加购物车
                cartProvider.addData(goods_bean,number);

                Log.e("TAG", "66:" + goods_bean.toString());
                Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }
        });


        //PopupWindowde 监听
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        initFinds();

        cartProvider = CartProvider.getInstance();
        //取出intent
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goods_bean != null) {
            setDataFormView(goods_bean);
        }

    }

    private void setDataFormView(GoodsBean goodsBean) {

        //取出商品详情信息
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        String product_id = goodsBean.getProduct_id();
        //设置图片信息
        Picasso.with(this).load(Constants.BASE_URL_IMAGE + figure).into(ivGoodInfoImage);
        //有信息就设置
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("￥" + cover_price);
        }

        setWebView(product_id);
    }

    private void setWebView(String product_id) {
        if(product_id != null){
            //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            //启用支持javascript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);

            //优先使用缓存
            wbGoodInfoMore.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }


}
