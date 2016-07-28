package com.jiqu.helper.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.view.FragmentAndViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/12.
 */
public class AppRankListFragment extends BaseFragment{
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAndViewPager fragmentViewPager;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.app_rank_list_layout,null);
        return view;
    }

    @Override
    public void initView() {
        fragmentViewPager = (FragmentAndViewPager) view.findViewById(R.id.fragmentViewPager);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        fragmentViewPager.setFragmentList(fragmentList);
        fragmentViewPager.setFragmentManager(getChildFragmentManager());
        String[] tabs = new String[]{"单机榜","网游榜","新游榜","热门榜","免费榜"};
        fragmentViewPager.setTab(tabs);
        fragmentViewPager.initData();
    }
}
