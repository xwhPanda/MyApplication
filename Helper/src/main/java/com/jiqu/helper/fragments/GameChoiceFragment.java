package com.jiqu.helper.fragments;

import android.view.LayoutInflater;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.MyImageButton;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class GameChoiceFragment extends BaseFragment{
    private SimpleDraweeView adOne,adTwo;
    private MyImageButton newGame,artifact,schoolgirl,online;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.game_choice_layout,null);
        return view;
    }

    @Override
    public void initView() {
        adOne = (SimpleDraweeView) view.findViewById(R.id.adOne);
        adTwo = (SimpleDraweeView) view.findViewById(R.id.adTwo);
        newGame = (MyImageButton) view.findViewById(R.id.newGame);
        artifact = (MyImageButton) view.findViewById(R.id.artifact);
        schoolgirl = (MyImageButton) view.findViewById(R.id.schoolgirl);
        online = (MyImageButton) view.findViewById(R.id.online);
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewHeight(adOne, MetricsTool.Rx * 340);
        UIUtil.setViewHeight(adTwo, MetricsTool.Rx * 340);
        UIUtil.setViewHeight(newGame, MetricsTool.Rx * 50);
        UIUtil.setViewHeight(artifact, MetricsTool.Rx * 50);
        UIUtil.setViewHeight(schoolgirl, MetricsTool.Rx * 50);
        UIUtil.setViewHeight(online, MetricsTool.Rx * 50);
    }

    @Override
    public void initData() {

    }
}
