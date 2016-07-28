package com.jiqu.helper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.helper.tools.MetricsTool;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected float Rx,Ry;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        Rx = MetricsTool.Rx;
        Ry = MetricsTool.Ry;

        view = getContentView();
        initView();
        initViewSize();
        initData();
        return view;
    }

    /** 获取布局ID **/
    public abstract View getContentView();
    /** 初始化控件 **/
    public abstract void initView();
    /** 初始话控件大小 **/
    public abstract void initViewSize();
    /** 初始化数据 **/
    public abstract void initData();
}
