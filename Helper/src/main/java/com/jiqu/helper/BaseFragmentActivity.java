package com.jiqu.helper;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.jiqu.helper.tools.MetricsTool;

/**
 * Created by xiongweihua on 2016/7/5.
 */
public abstract class BaseFragmentActivity extends FragmentActivity{

    protected  float Rx,Ry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        if (getContentView() != 0){
            setContentView(getContentView());
        }
        initView();
        initViewSize();
        initData();
    }

    public void init(){
        /** 沉浸式菜单栏，顶部菜单栏跟APP颜色保持一致 **/
        if (Build.VERSION.SDK_INT >= 19) {
            /** 顶部通知栏透明 **/
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);.
            getWindow().addFlags(67108864);
            /** 底部状态栏透明 **/
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        /** 初始化屏幕分辨率 **/
        MetricsTool.initMetrics(getWindowManager());
        Rx = MetricsTool.Rx;
        Ry = MetricsTool.Ry;
    }

    /** 获取布局ID **/
    public abstract int getContentView();
    /** 初始化控件 **/
    public abstract void initView();
    /** 初始话控件大小 **/
    public abstract void initViewSize();
    /** 初始化数据 **/
    public abstract void initData();
}
