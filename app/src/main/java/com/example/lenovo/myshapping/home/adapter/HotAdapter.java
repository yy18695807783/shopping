package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.home.bean.ResultBean;
import com.example.lenovo.myshapping.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 颜银 on 2016/11/19.
 * QQ:443098360
 * 微信：y443098360
 * 作用：
 */
public class HotAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBean.HotInfoBean> mHot_info;

    public HotAdapter(Context context, List<ResultBean.HotInfoBean> hot_info) {
        this.mContext = context;
        this.mHot_info = hot_info;
    }

    @Override
    public int getCount() {
        return mHot_info == null ? 0 : mHot_info.size();
    }

    @Override
    public Object getItem(int position) {
        return mHot_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //获取数据
        ResultBean.HotInfoBean hotInfoBean = mHot_info.get(position);

        //装配数据
        Picasso.with(mContext).load(Constants.BASE_URL_IMAGE + hotInfoBean.getFigure()).into(viewHolder.ivHot);

        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + hotInfoBean.getCover_price());

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.iv_hot)
        ImageView ivHot;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
