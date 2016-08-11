package com.jiqu.helper.fragments;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.view.FragmentAndViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/5.
 */
public class AppFragment extends BaseFragment{
    private FragmentAndViewPager fragmentAndViewPager;
    private AppChoiceFragment appChoiceFragment;
    private AppClassificationFragment appClassificationFragment;
    private AppRankingFragment appRankingFragment;
    private AppNewApplicationFragment appNewApplicationFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.app,null);
        return view;
    }

    @Override
    public void initView() {
        fragmentAndViewPager = (FragmentAndViewPager) view.findViewById(R.id.fragmentAndViewPager);

        appChoiceFragment = new AppChoiceFragment();
        appClassificationFragment = new AppClassificationFragment();
        appRankingFragment = new AppRankingFragment();
        appNewApplicationFragment = new AppNewApplicationFragment();
        fragmentList.add(appChoiceFragment);
        fragmentList.add(appClassificationFragment);
        fragmentList.add(appRankingFragment);
        fragmentList.add(appNewApplicationFragment);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        fragmentAndViewPager.setFragmentManager(getChildFragmentManager());
        fragmentAndViewPager.setFragmentList(fragmentList);
        String[] tabs = new String[]{"精选","分类","排行","新锐"};
        fragmentAndViewPager.setTab(tabs);
        fragmentAndViewPager.initData();
    }
}