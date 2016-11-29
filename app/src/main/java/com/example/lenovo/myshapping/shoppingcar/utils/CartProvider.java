package com.example.lenovo.myshapping.shoppingcar.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.lenovo.myshapping.app.MyAppliction;
import com.example.lenovo.myshapping.home.bean.GoodsBean;
import com.example.lenovo.myshapping.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * 购物车数据存储类
 */
public class CartProvider {
    public static final String JSON_CART = "json_cart";
    private Context context;
    //优化过的HashMap集合
    private SparseArray<GoodsBean> datas;

    private static CartProvider cartProvider;

    private CartProvider(Context context) {
        this.context = context;
        datas = new SparseArray<>(100);
        listToSparse();
    }

    public static CartProvider getInstance() {
        if (cartProvider == null) {
            cartProvider = new CartProvider(MyAppliction.getContext());
        }
        return cartProvider;
    }

    private void listToSparse() {
        List<GoodsBean> carts = getAllData();
        //放到sparseArry中
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                GoodsBean goodsBean = carts.get(i);
                datas.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
            }
        }
    }


    private List<GoodsBean> parsesToList() {
        List<GoodsBean> carts = new ArrayList<>();
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean shoppingCart = datas.valueAt(i);
                carts.add(shoppingCart);
            }
        }
        return carts;
    }

    public List<GoodsBean> getAllData() {
        return getDataFromLocal();
    }

    //本地获取json数据，并且通过Gson解析成list列表数据
    public List<GoodsBean> getDataFromLocal() {
        List<GoodsBean> carts = new ArrayList<>();
        //从本地获取缓存数据
        String savaJson = CacheUtils.getString(context, JSON_CART);
        if (!TextUtils.isEmpty(savaJson)) {
            //把数据转换成列表
            carts = new Gson().fromJson(savaJson, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        return carts;

    }

    //此地参数设置+1  变为多少  传过来
    public void addData(GoodsBean cart, int number) {

        //添加数据
        GoodsBean tempCart = datas.get(Integer.parseInt(cart.getProduct_id()));
        if (tempCart != null) {
            tempCart.setNumber(tempCart.getNumber() + number);
        } else {
            tempCart = cart;
            tempCart.setNumber(number);
        }

        datas.put(Integer.parseInt(tempCart.getProduct_id()), tempCart);

        //保存数据
        commit();
    }

    //保存数据
    private void commit() {
        //把parseArray转换成list
        List<GoodsBean> carts = parsesToList();
        //把转换成String
        String json = new Gson().toJson(carts);

        // 保存
        CacheUtils.putString(context, JSON_CART, json);

    }


    //删除数据
    public void deleteData(GoodsBean cart) {

        //删除数据

        datas.delete(Integer.parseInt(cart.getProduct_id()));


        //保存数据
        commit();
    }

    //修改数据
    public void updataData(GoodsBean cart) {
        //修改数据
        datas.put(Integer.parseInt(cart.getProduct_id()), cart);
        //保存数据
        commit();
    }

}
