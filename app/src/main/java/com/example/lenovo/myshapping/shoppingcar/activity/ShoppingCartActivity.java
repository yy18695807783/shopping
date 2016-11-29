package com.example.lenovo.myshapping.shoppingcar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.app.MainActivity;
import com.example.lenovo.myshapping.home.bean.GoodsBean;
import com.example.lenovo.myshapping.shoppingcar.adapter.ShopCartAdapter;
import com.example.lenovo.myshapping.shoppingcar.utils.CartProvider;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_shopcart_back)
    ImageButton ibShopcartBack;
    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @Bind(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    @Bind(R.id.checkbox_all)
    CheckBox checkboxAll;
    @Bind(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @Bind(R.id.btn_check_out)
    Button btnCheckOut;
    @Bind(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @Bind(R.id.cb_all)
    CheckBox cbAll;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.btn_collection)
    Button btnCollection;
    @Bind(R.id.ll_delete)
    LinearLayout llDelete;


    private ShopCartAdapter adapter;

    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 0;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 1;




    private void initFinds() {
        ibShopcartBack.setOnClickListener(this);
        btnCheckOut.setOnClickListener(this);
        tvShopcartEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        tvEmptyCartTobuy.setClickable(true);
        tvEmptyCartTobuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibShopcartBack) {
            finish();
        } else if (v == btnCheckOut) {
            Toast.makeText(ShoppingCartActivity.this, "结算", Toast.LENGTH_SHORT).show();


        } else if (v == tvShopcartEdit) {
            //设置编辑的点击事件
            int tag = (int) tvShopcartEdit.getTag();
            if (tag == ACTION_EDIT) {
                //变成完成状态
                showDelete();
            } else {
                //变成编辑状态
                hideDelete();
            }
        } else if (v == btnDelete) {
            adapter.deleteData();
            adapter.showTotalPrice();
            //显示空空如也
            checkData();
            adapter.checkAll();
        } else if (v == tvEmptyCartTobuy) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);

        adapter.checkAll_none(true);
        llDelete.setVisibility(View.GONE);
        llCheckAll.setVisibility(View.VISIBLE);

        adapter.showTotalPrice();
    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);

        adapter.checkAll_none(false);
        cbAll.setChecked(false);
        checkboxAll.setChecked(false);

        llDelete.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);

        adapter.showTotalPrice();
    }

    private void checkData() {
        if (adapter != null && adapter.getItemCount() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llEmptyShopcart.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.GONE);
        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
            tvShopcartEdit.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.GONE);
            llDelete.setVisibility(View.GONE);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        initFinds();


        //显示购物车产品--不写会点完添加商品后点击到购物车，会不显示当前添加的产品
        showData();

        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
    }

    private void showData() {
        CartProvider cartProvider = CartProvider.getInstance();

        List<GoodsBean> datas = cartProvider.getDataFromLocal();
        if (datas != null && datas.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llEmptyShopcart.setVisibility(View.GONE);
            adapter = new ShopCartAdapter(this, datas, tvShopcartTotal, cartProvider, checkboxAll, cbAll);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(adapter);
        } else {
            //显示空的
            tvShopcartEdit.setVisibility(View.GONE);
            llEmptyShopcart.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.GONE);
            llDelete.setVisibility(View.GONE);
        }
    }



}
