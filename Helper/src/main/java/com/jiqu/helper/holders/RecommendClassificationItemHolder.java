package com.jiqu.helper.holders;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.RecommendClassificationInfo;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/11.
 */
public class RecommendClassificationItemHolder {
    public SimpleDraweeView icon;
    public TextView name;
    private View rootView;
    private RecommendClassificationInfo data;

    public RecommendClassificationItemHolder(Context context){

        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_classification_gridview_item_layout,null);
        icon = (SimpleDraweeView) view.findViewById(R.id.icon);
        name = (TextView) view.findViewById(R.id.name);
        initViewSize();
        rootView = view;
    }

    public View getRootView(){
        return  rootView;
    }

    public void setData(RecommendClassificationInfo data){
        this.data = data;
        Uri uri = Uri.parse(data.getPic());
        icon.setImageURI(uri);
        name.setText(data.getName());
    }

    private void initViewSize(){
        try {
            UIUtil.setViewSize(icon, MetricsTool.Rx * 150,MetricsTool.Rx * 150);
            UIUtil.setTextSize(name,40);
            UIUtil.setViewSizeMargin(name,0,MetricsTool.Rx * 15,0,0);
            UIUtil.setViewSizeMargin(icon,0,MetricsTool.Rx * 40,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
