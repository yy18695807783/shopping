package com.example.lenovo.myshapping.type.fragment;

import android.util.Log;
import android.widget.GridView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.type.adapter.TagGridViewAdapter;
import com.example.lenovo.myshapping.type.bean.TagBean;
import com.example.lenovo.myshapping.utils.Constants;
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
 * 作用：
 */
public class TagFragment extends BaseFragment {

    @Bind(R.id.gv_tag)
    GridView gvTag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> result;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.TAG_URL)//http://192.168.1.144:8080/atguigu/json/TAG_URL.json
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
                adapter = new TagGridViewAdapter(mContext, result);
                gvTag.setAdapter(adapter);
            }
        }
    }

    private void processData(String json) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        result = tagBean.getResult();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

}
