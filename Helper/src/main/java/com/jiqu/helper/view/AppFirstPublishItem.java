package com.jiqu.helper.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/8/11.
 */
public class AppFirstPublishItem extends LinearLayout{
    private SimpleDraweeView icon;
    private TextView name;
    private LinearLayout downloadLin;
    private TextView size;

    public AppFirstPublishItem(Context context) {
        super(context);
        initView(context);
    }

    public AppFirstPublishItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AppFirstPublishItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.app_first_publish_item_layout,this);
        icon = (SimpleDraweeView) view.findViewById(R.id.icon);
        name = (TextView) view.findViewById(R.id.name);
        downloadLin = (LinearLayout) view.findViewById(R.id.downloadLin);
        size = (TextView) view.findViewById(R.id.size);

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewSize(icon,MetricsTool.Rx * 210,MetricsTool.Rx * 210);
        UIUtil.setViewSize(downloadLin, MetricsTool.Rx * 252,MetricsTool.Rx * 80);
        try {
            UIUtil.setViewSizeMargin(icon,0,MetricsTool.Rx * 65,0,MetricsTool.Rx * 35);
            UIUtil.setViewSizeMargin(downloadLin,0,MetricsTool.Rx * 30,0,MetricsTool.Rx * 65);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(GameInfo info){
        icon.setImageURI(Uri.parse(info.getIcon()));
        name.setText(info.getApply_name());
        size.setText(String.valueOf(info.getSize()));
    }
}
