package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.home.actyvity.GoodsListActivity;
import com.example.lenovo.myshapping.home.bean.TypeListBean;
import com.example.lenovo.myshapping.utils.MyConstants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;

    public GoodsListAdapter(GoodsListActivity mContext, List<TypeListBean.ResultBean.PageDataBean> page_data) {
        this.mContext = mContext;
        this.page_data = page_data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_goods_list_adapter, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(page_data.get(position));

    }

    @Override
    public int getItemCount() {
        return page_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;
        private TypeListBean.ResultBean.PageDataBean data;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_hot = (ImageView) itemView.findViewById(R.id.iv_hot);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.setOnItemClickListener(data);
                    }
                }
            });
        }

        public void setData(TypeListBean.ResultBean.PageDataBean data) {
            Picasso.with(mContext).load(MyConstants.BASE_URL_IMAGE+data.getFigure()).into(iv_hot);
            tv_name.setText(data.getName());
            tv_price.setText("￥" + data.getCover_price());
            this.data = data;

        }
    }


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(TypeListBean.ResultBean.PageDataBean data);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
