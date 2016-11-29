package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.myshapping.home.bean.ResultBean;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 颜银 on 2016/11/19.
 * QQ:443098360
 * 微信：y443098360
 * 作用：
 */
public class ActAdapter extends PagerAdapter {

    private Context mContext;
    private List<ResultBean.ActInfoBean> mAct_info;


    public ActAdapter(Context context, List<ResultBean.ActInfoBean> act_info) {
        this.mContext = context;
        this.mAct_info = act_info;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.FIT_XY);

        //绑定数据
        Picasso.with(mContext).load(MyConstants.BASE_URL_IMAGE + mAct_info.get(position).getIcon_url()).into(view);
//        Glide.with(mContext)
//                .load(MyConstants.Base_URl_IMAGE + data.get(position).getIcon_url())
//                .into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mAct_info == null ? 0 : mAct_info.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
