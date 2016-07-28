package com.jiqu.helper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public class HomeTopView extends RelativeLayout{
    private Button account;
    private RelativeLayout searchRel;
    private EditText searchEdit;
    private Button searchBtn;

    public HomeTopView(Context context) {
        super(context);
        initView(context);
    }

    public HomeTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.home_top_layout,this);
        account = (Button) view.findViewById(R.id.account);
        searchRel = (RelativeLayout) view.findViewById(R.id.searchRel);
        searchEdit = (EditText) view.findViewById(R.id.searchEdit);
        searchBtn = (Button) view.findViewById(R.id.searchBtn);

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewSize(account, MetricsTool.Rx * 60 ,MetricsTool.Rx * 60);
        UIUtil.setViewSize(searchBtn, MetricsTool.Rx * 55 ,MetricsTool.Rx * 55);
        UIUtil.setViewSize(searchRel,MetricsTool.Rx * 840,MetricsTool.Rx * 70);
        UIUtil.setViewSize(searchEdit,MetricsTool.Rx * 750,MetricsTool.Rx * 60);

        UIUtil.setTextSize(searchEdit,30);

        try {
            UIUtil.setViewSizeMargin(searchRel,MetricsTool.Rx * 30,MetricsTool.Rx * 50,0,MetricsTool.Rx * 50);
            UIUtil.setViewSizeMargin(searchEdit,MetricsTool.Rx * 15,0,0,0);
            UIUtil.setViewSizeMargin(searchBtn,0,0,MetricsTool.Rx * 15,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
