package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.home.bean.ResultBean;
import com.example.lenovo.myshapping.utils.MyConstants;
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
public class RecommendGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBean.RecommendInfoBean> mRecommend_info;

    public RecommendGridViewAdapter(Context context, List<ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = context;
        this.mRecommend_info = recommend_info;
    }

    @Override
    public int getCount() {
        return mRecommend_info == null? 0 : mRecommend_info.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecommend_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.item_recommend,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //获取数据
        ResultBean.RecommendInfoBean recommendInfoBean = mRecommend_info.get(position);

        //绑定数据
        Picasso.with(mContext).load(MyConstants.BASE_URL_IMAGE + recommendInfoBean.getFigure()).into(viewHolder.ivRecommend);
        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        return convertView;

    }
    class ViewHolder {
        @Bind(R.id.iv_recommend)
        ImageView ivRecommend;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
