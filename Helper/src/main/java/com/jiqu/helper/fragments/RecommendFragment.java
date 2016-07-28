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
public class RecommendFragment extends BaseFragment{
    private HomeChoiceFragment homeChoiceFragment;
    private HomeClassificationFragment homeClassificationFragment;
    private HomeListFragment homeListFragment;
    private HomeFindingsFragment homeFindingsFragment;
    private HomePrerequisitesFragment homePrerequisitesFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    private FragmentAndViewPager recommendViewPager;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.recommend,null);
        return view;
    }

    @Override
    public void initView() {
        homeChoiceFragment = new HomeChoiceFragment();
        homeClassificationFragment = new HomeClassificationFragment();
        homeListFragment = new HomeListFragment();
        homeFindingsFragment = new HomeFindingsFragment();
        homePrerequisitesFragment = new HomePrerequisitesFragment();
        fragmentList.add(homeChoiceFragment);
        fragmentList.add(homeClassificationFragment);
        fragmentList.add(homeListFragment);
        fragmentList.add(homeFindingsFragment);
        fragmentList.add(homePrerequisitesFragment);

        recommendViewPager = (FragmentAndViewPager) view.findViewById(R.id.recommendViewPager);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        /** initData需要最后调用 **/
        recommendViewPager.setFragmentList(fragmentList);
        recommendViewPager.setFragmentManager(getChildFragmentManager());
        String[] tabs = new String[]{"精选","分类","榜单","发现","必备"};
        recommendViewPager.setTab(tabs);
        recommendViewPager.initData();
    }
}
