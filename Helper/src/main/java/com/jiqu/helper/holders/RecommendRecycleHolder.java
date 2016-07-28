package com.jiqu.helper.holders;

import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/7.
 */
public class RecommendRecycleHolder extends BaseHolder{
    public SimpleDraweeView icon;

    public RecommendRecycleHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void initView() {
        icon = (SimpleDraweeView) itemView.findViewById(R.id.icon);

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewSize(icon, MetricsTool.Rx * 110,MetricsTool.Rx * 110);
    }
}
