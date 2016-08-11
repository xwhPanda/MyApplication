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
public class GameFragment extends BaseFragment{
    private FragmentAndViewPager gameViewPager;
    private GameChoiceFragment gameChoiceFragment;
    private GameClassificationFragment gameClassificationFragment;
    private GameRankingFragment gameRankingFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.game,null);
        return view;
    }

    @Override
    public void initView() {
        gameChoiceFragment = new GameChoiceFragment();
        gameClassificationFragment = new GameClassificationFragment();
        gameRankingFragment = new GameRankingFragment();
        fragmentList.add(gameChoiceFragment);
        fragmentList.add(gameClassificationFragment);
        fragmentList.add(gameRankingFragment);

        gameViewPager = (FragmentAndViewPager) view.findViewById(R.id.gameViewPager);
    }

    @Override
    public void initViewSize() {
    }

    @Override
    public void initData() {
        gameViewPager.setFragmentList(fragmentList);
        gameViewPager.setFragmentManager(getChildFragmentManager());
        String[] tabs = new String[]{"精选","分类","排行"};
        gameViewPager.setTab(tabs);
        gameViewPager.initData();
    }
}