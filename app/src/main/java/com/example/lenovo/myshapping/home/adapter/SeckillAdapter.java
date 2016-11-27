package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class SeckillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ResultBean.SeckillInfoBean mSeckill_info;
    private final List<ResultBean.SeckillInfoBean.ListBean> list;

    public SeckillAdapter(Context context, ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = context;
        this.mSeckill_info = seckill_info;
        list = seckill_info.getList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        return new MyViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
        //加载图片
        Picasso.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(myViewHolder.ivFigure);
        myViewHolder.tvCoverPrice.setText("￥" + listBean.getCover_price());
        myViewHolder.tvOriginPrice.setText("￥" + listBean.getOrigin_price());


        //设置点击事件
        myViewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSeckillRecyclerView != null){
                    onSeckillRecyclerView.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_figure)
        ImageView ivFigure;
        @Bind(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @Bind(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @Bind(R.id.ll_root)
        LinearLayout llRoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 点击秒杀商品时的监听接口回调方法
     */
    public interface OnSeckillRecyclerView{
        /**
         * 点击的是那条
         * @param position
         */
        void onClick(int position);
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    /**
     * 秒杀商品recycleView的点击事件设置方法
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
