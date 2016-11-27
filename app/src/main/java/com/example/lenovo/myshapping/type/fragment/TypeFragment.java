package com.example.lenovo.myshapping.type.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：分类
 */
public class TypeFragment extends BaseFragment {

    @Bind(R.id.rb_choose_list)
    RadioButton rbChooseList;
    @Bind(R.id.rb_choose_tag)
    RadioButton rbChooseTag;
    @Bind(R.id.rg_type_choose)
    RadioGroup rgTypeChoose;
    @Bind(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @Bind(R.id.fl_type)
    FrameLayout flType;



    private ListFragment listFragment;
    private TagFragment tagFragment;
    private FragmentTransaction transaction;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData() {


    }



    @Override
    protected void initListener() {

        rgTypeChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_choose_list:
                        switchFragment(0);
                        break;
                    case R.id.rb_choose_tag:
                        switchFragment(1);
                        break;
                }
            }
        });

        //默认选择第一个
        rgTypeChoose.check(R.id.rb_choose_list);


        ivTypeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "分类---搜索", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchFragment(int position) {


        switch (position) {
            case 0:
                showFragment(0);//选择分类
                break;
            case 1:
                showFragment(1);//选择标签
                break;
        }

    }

    private void showFragment(int i) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();

        hideFragment();//隐藏fragment

        if (i == 0) {
            if (listFragment == null) {
                listFragment = new ListFragment();
                transaction.add(R.id.fl_type, listFragment);
            }
            transaction.show(listFragment);

        } else if (i == 1) {
            if (tagFragment == null) {
                tagFragment = new TagFragment();
                transaction.add(R.id.fl_type, tagFragment);
            }
            transaction.show(tagFragment);

        }
        transaction.commit();

    }

    /**
     * 隐藏Fragment
     */
    private void hideFragment() {

        //隐藏
        if (listFragment != null) {
            transaction.hide(listFragment);
        }
        if (tagFragment != null) {
            transaction.hide(tagFragment);
        }
    }


}
