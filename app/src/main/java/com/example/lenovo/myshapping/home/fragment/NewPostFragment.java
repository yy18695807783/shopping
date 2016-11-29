package com.example.lenovo.myshapping.home.fragment;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.find.adapter.NewPostListViewAdapter;
import com.example.lenovo.myshapping.find.bean.NewPostBean;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by 颜银 on 2016/11/21.
 * QQ:443098360
 * 微信：y443098360
 * 作用：
 */
public class NewPostFragment extends BaseFragment {

    @Bind(R.id.lv_new_post)
    ListView lv_new_post;
    private List<NewPostBean.ResultBean> result;




    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_post;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(MyConstants.NEW_POST_URL)//http://192.168.1.144:8080/atguigu/json/NEW_POST_URL.json
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {


        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            switch (id) {
                case 100:
//                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response);
                        NewPostListViewAdapter adapter = new NewPostListViewAdapter(mContext, result);
                        lv_new_post.setAdapter(adapter);
                    }
                    break;
                case 101:
                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void processData(String json) {
        Gson gson = new Gson();
        NewPostBean newPostBean = gson.fromJson(json, NewPostBean.class);
        result = newPostBean.getResult();
    }
}
