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
public class HomeTopView extends RelativeLayout implements View.OnClickListener{
    private Button account;
    private RelativeLayout searchRel;
    private EditText searchEdit;
    private Button searchBtn;
    private OnClickListener editListener,btnListener,accountListener;

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

        searchEdit.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        account.setOnClickListener(this);
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

    public void setSearchEditListener(OnClickListener listener){
        this.editListener = listener;
    }

    public void setAccountListener(OnClickListener listener){
        this.accountListener = listener;
    }

    public void setBtnListener(OnClickListener listener){
        this.btnListener = listener;
    }

    public String getEditText(){
        return searchEdit.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchEdit:
                if (editListener != null)
                    editListener.onClick(v);
                break;
            case R.id.searchBtn:
                if (btnListener != null)
                    btnListener.onClick(v);
                break;
            case R.id.account:
                if (accountListener != null)
                    accountListener.onClick(v);
                break;
        }
    }
}
