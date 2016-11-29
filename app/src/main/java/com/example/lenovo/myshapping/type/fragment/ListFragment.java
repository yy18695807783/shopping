package com.example.lenovo.myshapping.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.type.adapter.TypeLeftAdapter;
import com.example.lenovo.myshapping.type.adapter.TypeRightAdapter;
import com.example.lenovo.myshapping.type.bean.TypeBean;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by 颜银 on 2016/11/22.
 * QQ:443098360
 * 微信：y443098360
 * 作用：分类中的 分类Fragment
 */
public class ListFragment extends BaseFragment {
    @Bind(R.id.lv_left)
    ListView lvLeft;
    @Bind(R.id.rv_right)
    RecyclerView rvRight;

    private List<TypeBean.ResultBean> result;
    private String[] urls = new String[]{MyConstants.SKIRT_URL,//http://192.168.1.144:8080/atguigu/json/SKIRT_URL.json
            MyConstants.JACKET_URL,
            MyConstants.PANTS_URL,
            MyConstants.OVERCOAT_URL,
            MyConstants.ACCESSORY_URL,
            MyConstants.BAG_URL,
            MyConstants.DRESS_UP_URL,
            MyConstants.HOME_PRODUCTS_URL,
            MyConstants.STATIONERY_URL,
            MyConstants.DIGIT_URL,
            MyConstants.GAME_URL};

    private TypeLeftAdapter leftAdapter;
    private boolean isFirst = true;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        //默认选择显示第一条信息
        getDataFromNet(urls[0]);

    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
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
            if (response != null) {
                processData(response);
                if (isFirst) {
                    leftAdapter = new TypeLeftAdapter(mContext);
                    lvLeft.setAdapter(leftAdapter);
                }
                initListenerFoSelf(leftAdapter);//左边listView的点击监听以及选中监听

                //解析右边数据
                TypeRightAdapter rightAdapter = new TypeRightAdapter(mContext, result);
                rvRight.setAdapter(rightAdapter);

                //设置RecycleView三列
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

                //HomeFrasgment  设置跨度大小查找----如果去掉，分类中热卖推存就不能左右滑动(其实只是在第一个商品空格中滑动)
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position == 0) {
                            return 3;//设置第一行为3列显示
                        } else {
                            return 1;//其余行是一列（其中添加3列的GlideView，还能显示）
                        }
                    }
                });
                rvRight.setLayoutManager(manager);
            }
        }
    }

    private void initListenerFoSelf(final TypeLeftAdapter adapter) {
        //点击监听
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.changeSelected(position);//刷新选择的背景字体特效

                if (position != 0) {
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });

        //选中监听
        lvLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void processData(String json) {
        Gson gson = new Gson();
        TypeBean typeBean = gson.fromJson(json, TypeBean.class);
        result = typeBean.getResult();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

}
