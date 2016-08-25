package com.jiqu.helper.fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.ClearCacheActivity;
import com.jiqu.helper.activity.UninstallActivity;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.CircleProgressView;

/**
 * Created by xiongweihua on 2016/7/5.
 */
public class ToolFragment extends BaseFragment implements View.OnClickListener{
    private ImageView accountBtn,settingBtn,wifiBtn;
    private CircleProgressView progressView;
    private LinearLayout uninstallLin;
    private LinearLayout clearLin;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.tool,null);
        return view;
    }

    @Override
    public void initView() {
        accountBtn = (ImageView) view.findViewById(R.id.accountBtn);
        settingBtn = (ImageView) view.findViewById(R.id.settingBtn);
        wifiBtn = (ImageView) view.findViewById(R.id.wifiBtn);
        progressView = (CircleProgressView) view.findViewById(R.id.progressView);
        uninstallLin = (LinearLayout) view.findViewById(R.id.uninstallLin);
        clearLin = (LinearLayout) view.findViewById(R.id.clearLin);

        accountBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        wifiBtn.setOnClickListener(this);
        uninstallLin.setOnClickListener(this);
        clearLin.setOnClickListener(this);

    }

    @Override
    public void initViewSize() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uninstallLin:
                startActivity(new Intent(mActivity, UninstallActivity.class));
                break;
            case R.id.clearLin:
                startActivity(new Intent(mActivity, ClearCacheActivity.class));
                break;
        }
    }
}
