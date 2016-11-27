package com.example.lenovo.myshapping.find.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.base.BaseFragment;
import com.example.lenovo.myshapping.find.adapter.CommunityViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.Bind;

/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：发现
 */
public class FindFragment extends BaseFragment {


    @Bind(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @Bind(R.id.pager_indicator)
    TabPageIndicator pagerIndicator;
    @Bind(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @Bind(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {
        CommunityViewPagerAdapter adapter = new CommunityViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        //要在viewPager设置适配器之后  上边的选择标签

        pagerIndicator.setVisibility(View.VISIBLE);
        pagerIndicator.setViewPager(viewPager);
    }


    @Override
    protected void initListener() {

    }

}
