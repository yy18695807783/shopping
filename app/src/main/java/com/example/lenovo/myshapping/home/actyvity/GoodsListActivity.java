package com.example.lenovo.myshapping.home.actyvity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.app.GoodsInfoActivity;
import com.example.lenovo.myshapping.home.adapter.ExpandableListViewAdapter;
import com.example.lenovo.myshapping.home.adapter.GoodsListAdapter;
import com.example.lenovo.myshapping.home.bean.GoodsBean;
import com.example.lenovo.myshapping.home.bean.TypeListBean;
import com.example.lenovo.myshapping.home.utils.SpaceItemDecoration;
import com.example.lenovo.myshapping.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class GoodsListActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @Bind(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @Bind(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @Bind(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @Bind(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @Bind(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @Bind(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @Bind(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @Bind(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.ib_drawer_layout_back)
    ImageButton ibDrawerLayoutBack;
    @Bind(R.id.tv_ib_drawer_layout_title)
    TextView tvIbDrawerLayoutTitle;
    @Bind(R.id.ib_drawer_layout_confirm)
    TextView ibDrawerLayoutConfirm;
    @Bind(R.id.rb_select_hot)
    RadioButton rbSelectHot;
    @Bind(R.id.rb_select_new)
    RadioButton rbSelectNew;
    @Bind(R.id.rg_select)
    RadioGroup rgSelect;
    @Bind(R.id.tv_drawer_price)
    TextView tvDrawerPrice;
    @Bind(R.id.rl_select_price)
    RelativeLayout rlSelectPrice;
    @Bind(R.id.tv_drawer_recommend)
    TextView tvDrawerRecommend;
    @Bind(R.id.rl_select_recommend_theme)
    RelativeLayout rlSelectRecommendTheme;
    @Bind(R.id.tv_drawer_type)
    TextView tvDrawerType;
    @Bind(R.id.rl_select_type)
    RelativeLayout rlSelectType;
    @Bind(R.id.btn_select_all)
    Button btnSelectAll;
    @Bind(R.id.ll_select_root)
    LinearLayout llSelectRoot;
    @Bind(R.id.btn_drawer_layout_cancel)
    Button btnDrawerLayoutCancel;
    @Bind(R.id.btn_drawer_layout_confirm)
    Button btnDrawerLayoutConfirm;
    @Bind(R.id.iv_price_no_limit)
    ImageView ivPriceNoLimit;
    @Bind(R.id.rl_price_nolimit)
    RelativeLayout rlPriceNolimit;
    @Bind(R.id.iv_price_0_15)
    ImageView ivPrice015;
    @Bind(R.id.rl_price_0_15)
    RelativeLayout rlPrice015;
    @Bind(R.id.iv_price_15_30)
    ImageView ivPrice1530;
    @Bind(R.id.rl_price_15_30)
    RelativeLayout rlPrice1530;
    @Bind(R.id.iv_price_30_50)
    ImageView ivPrice3050;
    @Bind(R.id.rl_price_30_50)
    RelativeLayout rlPrice3050;
    @Bind(R.id.iv_price_50_70)
    ImageView ivPrice5070;
    @Bind(R.id.rl_price_50_70)
    RelativeLayout rlPrice5070;
    @Bind(R.id.iv_price_70_100)
    ImageView ivPrice70100;
    @Bind(R.id.rl_price_70_100)
    RelativeLayout rlPrice70100;
    @Bind(R.id.iv_price_100)
    ImageView ivPrice100;
    @Bind(R.id.rl_price_100)
    RelativeLayout rlPrice100;
    @Bind(R.id.et_price_start)
    EditText etPriceStart;
    @Bind(R.id.v_price_line)
    View vPriceLine;
    @Bind(R.id.et_price_end)
    EditText etPriceEnd;
    @Bind(R.id.rl_select_price_start_end)
    RelativeLayout rlSelectPriceStartEnd;
    @Bind(R.id.ll_price_root)
    LinearLayout llPriceRoot;
    @Bind(R.id.btn_drawer_theme_cancel)
    Button btnDrawerThemeCancel;
    @Bind(R.id.btn_drawer_theme_confirm)
    Button btnDrawerThemeConfirm;
    @Bind(R.id.iv_theme_all)
    ImageView ivThemeAll;
    @Bind(R.id.rl_theme_all)
    RelativeLayout rlThemeAll;
    @Bind(R.id.iv_theme_note)
    ImageView ivThemeNote;
    @Bind(R.id.rl_theme_note)
    RelativeLayout rlThemeNote;
    @Bind(R.id.iv_theme_funko)
    ImageView ivThemeFunko;
    @Bind(R.id.rl_theme_funko)
    RelativeLayout rlThemeFunko;
    @Bind(R.id.iv_theme_gsc)
    ImageView ivThemeGsc;
    @Bind(R.id.rl_theme_gsc)
    RelativeLayout rlThemeGsc;
    @Bind(R.id.iv_theme_origin)
    ImageView ivThemeOrigin;
    @Bind(R.id.rl_theme_origin)
    RelativeLayout rlThemeOrigin;
    @Bind(R.id.iv_theme_sword)
    ImageView ivThemeSword;
    @Bind(R.id.rl_theme_sword)
    RelativeLayout rlThemeSword;
    @Bind(R.id.iv_theme_food)
    ImageView ivThemeFood;
    @Bind(R.id.rl_theme_food)
    RelativeLayout rlThemeFood;
    @Bind(R.id.iv_theme_moon)
    ImageView ivThemeMoon;
    @Bind(R.id.rl_theme_moon)
    RelativeLayout rlThemeMoon;
    @Bind(R.id.iv_theme_quanzhi)
    ImageView ivThemeQuanzhi;
    @Bind(R.id.rl_theme_quanzhi)
    RelativeLayout rlThemeQuanzhi;
    @Bind(R.id.iv_theme_gress)
    ImageView ivThemeGress;
    @Bind(R.id.rl_theme_gress)
    RelativeLayout rlThemeGress;
    @Bind(R.id.ll_theme_root)
    LinearLayout llThemeRoot;
    @Bind(R.id.btn_drawer_type_cancel)
    Button btnDrawerTypeCancel;
    @Bind(R.id.btn_drawer_type_confirm)
    Button btnDrawerTypeConfirm;
    @Bind(R.id.expandableListView)
    ExpandableListView listView;
    @Bind(R.id.ll_type_root)
    LinearLayout llTypeRoot;
    @Bind(R.id.dl_left)
    DrawerLayout dlLeft;

    private int click_count = 0;

    private int childP;
    private int groupP;

    private ExpandableListViewAdapter adapter;

    private ArrayList<String> group;
    private ArrayList<List<String>> child;

    private void initFinds() {
        ibGoodsListBack.setOnClickListener(this);//顶部--返回键
        ibGoodsListHome.setOnClickListener(this);//顶部--home键
        llGoodsListPrice.setOnClickListener(this);//顶部--价格布局键
        tvGoodsListSort.setOnClickListener(this);//顶部--综合排序键
        tvGoodsListSelect.setOnClickListener(this);//顶部--筛选键

        rbSelectHot.setOnClickListener(this);//筛选键--热卖
        rbSelectNew.setOnClickListener(this);//筛选键--新品
        btnSelectAll.setOnClickListener(this);//筛选键--重置

        rlSelectPrice.setOnClickListener(this);//筛选键--价格
        rlSelectRecommendTheme.setOnClickListener(this);//筛选键--推荐主题
        rlSelectType.setOnClickListener(this);//筛选键--类别

        ibDrawerLayoutBack.setOnClickListener(this);//筛选键--返回
        ibDrawerLayoutConfirm.setOnClickListener(this);//筛选键--确定

        btnDrawerLayoutCancel.setOnClickListener(this);//价格--取消键
        btnDrawerLayoutConfirm.setOnClickListener(this);//价格--确定键

        btnDrawerThemeCancel.setOnClickListener(this);//推存主题--取消键
        btnDrawerThemeConfirm.setOnClickListener(this);//推存主题--确定键

        btnDrawerTypeCancel.setOnClickListener(this);//类别--取消键
        btnDrawerTypeConfirm.setOnClickListener(this);//类别--确定键

    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodsListBack) {//顶部--返回键
            finish();

        } else if (v == ibGoodsListHome) {//顶部--home键
//            Constants.isBackHome = true;
//            if(dlLeft.isEnabled()){
//                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));//黑色
//            }
            finish();

        } else if (v == tvGoodsListSearch) {//顶部--价格布局键
            Toast.makeText(GoodsListActivity.this, "搜索", Toast.LENGTH_SHORT).show();

        } else if (v == llGoodsListPrice) {//顶部--价格布局键
            //价格点击事件
            click_count++;
            //综合排序变为默认
            tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
            //价格红
            tvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));
            if (click_count % 2 == 1) {
                // 箭头向下红
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
            } else {
                // 箭头向上红
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
            }

        } else if (v == tvGoodsListSort) {//顶部--综合排序键
            click_count = 0;
            ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
            tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));//黑色
            tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));//红色

        } else if (v == tvGoodsListSelect) {//顶部--筛选键
            tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));//红色
            dlLeft.openDrawer(Gravity.RIGHT);//打开右侧菜单

            llSelectRoot.setVisibility(View.VISIBLE);//显示筛选界面
            ibDrawerLayoutBack.setVisibility(View.VISIBLE);//显示筛选界面  返回键
            showSelectorLayout();//隐藏其他页面

        } else if (v == rbSelectHot) {//筛选键--热卖
            Toast.makeText(GoodsListActivity.this, "筛选键--热卖", Toast.LENGTH_SHORT).show();

        } else if (v == rbSelectNew) {//筛选键--新品
            Toast.makeText(GoodsListActivity.this, "筛选键--新品", Toast.LENGTH_SHORT).show();

        } else if (v == btnSelectAll) {//筛选键--重置
            Toast.makeText(GoodsListActivity.this, "筛选键--重置", Toast.LENGTH_SHORT).show();

        } else if (v == rlSelectPrice) {//筛选键--价格
            //价格筛选的页面
            llPriceRoot.setVisibility(View.VISIBLE);
            ibDrawerLayoutBack.setVisibility(View.GONE);

            showPriceLayout();

        } else if (v == rlSelectRecommendTheme) {//筛选键--推荐主题
            llThemeRoot.setVisibility(View.VISIBLE);
            ibDrawerLayoutBack.setVisibility(View.GONE);

            showThemeLayout();

        } else if (v == rlSelectType) {//筛选键--类别
            llTypeRoot.setVisibility(View.VISIBLE);
            ibDrawerLayoutBack.setVisibility(View.GONE);

            showTypeLayout();
        } else if (v == ibDrawerLayoutBack) {//筛选键--返回
            dlLeft.closeDrawers();
        } else if (v == ibDrawerLayoutConfirm) {
            Toast.makeText(GoodsListActivity.this, "筛选键--确定", Toast.LENGTH_SHORT).show();

        } else if (v == btnDrawerLayoutCancel) {//价格--取消键
            llSelectRoot.setVisibility(View.VISIBLE);//显示筛选界面
            ibDrawerLayoutBack.setVisibility(View.VISIBLE);//显示筛选界面 返回键
            showSelectorLayout();

        } else if (v == btnDrawerLayoutConfirm) {//价格--确定键
            Toast.makeText(GoodsListActivity.this, "价格--确定键", Toast.LENGTH_SHORT).show();

        } else if (v == btnDrawerThemeCancel) {//推存主题--取消键
            llSelectRoot.setVisibility(View.VISIBLE);//显示筛选界面
            showSelectorLayout();

        } else if (v == btnDrawerThemeConfirm) {//推存主题--确定键
            Toast.makeText(GoodsListActivity.this, "推存主题--确定键", Toast.LENGTH_SHORT).show();

        } else if (v == btnDrawerTypeCancel) {//类型--确定键
            llSelectRoot.setVisibility(View.VISIBLE);//显示筛选界面
            showSelectorLayout();

        } else if (v == btnDrawerTypeCancel) {//类型--确定键
            Toast.makeText(GoodsListActivity.this, "推存主题--确定键", Toast.LENGTH_SHORT).show();

        }
    }

    //筛选页面
    private void showSelectorLayout() {
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    //价格页面
    private void showPriceLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    //主题页面
    private void showThemeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
    }

    //类别页面
    private void showTypeLayout() {
        llSelectRoot.setVisibility(View.GONE);
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);

        //初始化ExpandableListView
        initExpandableListView();
        adapter = new ExpandableListViewAdapter(this, group, child);
        listView.setAdapter(adapter);
    }

    private void initExpandableListView() {
        group = new ArrayList<>();//用来记录组名字的集合
        child = new ArrayList<>();//用来装组的数据，每组数据又是一个集合
        //去掉默认箭头
        listView.setGroupIndicator(null);
        addInfo("全部", new String[]{});
        addInfo("上衣", new String[]{"古风", "和风", "lolita", "日常"});
        addInfo("下装", new String[]{"日常", "泳衣", "汉风", "lolita", "创意T恤"});
        addInfo("外套", new String[]{"汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"});

        // 这里是控制如果列表没有孩子菜单不展开的效果-----点击组的监听
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (child.get(groupPosition).isEmpty()) {// isEmpty没有
                    return true;
                } else {
                    return false;
                }
            }
        });

        //下面还有的点击监听-----点击组里面孩子的监听
    }

    /**
     * 添加数据信息
     *
     * @param g
     * @param c
     */
    private void addInfo(String g, String[] c) {
        group.add(g);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            list.add(c[i]);
        }
        child.add(list);
    }

    //-------------------------------------------以下是设置商品详情页面的recyclerview的数据信息解析过程-------------------------------------------

    /**
     * 以下是频道9个（出去一个加号）点击时不同分类的商品
     * <p/>
     * 数据信息见tomecat服务器
     */
    private int position;
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,//http://192.168.1.144:8080/atguigu/json/CLOSE_STORE.json
            Constants.GAME_STORE,//http://192.168.1.144:8080/atguigu/json/GAME_STORE.json
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private GoodsListAdapter adapter1;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);

        initFinds();

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        getDataFromNet();

        initListener();
    }

    private void initListener() {
        //显示上衣 、下装、外套里面子类的点击监听
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(GoodsListActivity.this, "childPosition" + childPosition, Toast.LENGTH_SHORT).show();
                childP = childPosition;
                adapter.notifyDataSetChanged();
                return false;
            }
        });

//        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(GoodsListActivity.this, "groupPosition" + groupPosition, Toast.LENGTH_SHORT).show();
//                groupP = groupPosition;
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(GoodsListActivity.this, "position---" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(urls[position])
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            switch (id) {
                case 100:
//                    Toast.makeText(GoodsListActivity.this, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response);

                        GridLayoutManager manager = new GridLayoutManager(GoodsListActivity.this, 2);
                        recyclerview.setLayoutManager(manager);

                        adapter1 = new GoodsListAdapter(GoodsListActivity.this, page_data);
//                        recyclerview.addItemDecoration(new DividerItemDecoration(GoodsListActivity.this, manager.getOrientation()));
                        recyclerview.addItemDecoration(new SpaceItemDecoration(10));
                        recyclerview.setAdapter(adapter1);

                        adapter1.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                            @Override
                            public void setOnItemClickListener(TypeListBean.ResultBean.PageDataBean data) {

                                //商品详情
                                String name = data.getName();
                                String cover_price = data.getCover_price();
                                String figure = data.getFigure();
                                String product_id = data.getProduct_id();

                                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                                Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                                intent.putExtra("goods_bean", goodsBean);
                                startActivity(intent);
                            }
                        });
                    }
                    break;
                case 101:
                    Toast.makeText(GoodsListActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void processData(String response) {

//        Gson gson = new Gson();
//        TypeListBean typeListBean = gson.fromJson(response, TypeListBean.class);

        TypeListBean typeListBean = JSON.parseObject(response, TypeListBean.class);
        page_data = typeListBean.getResult().getPage_data();
    }


}
