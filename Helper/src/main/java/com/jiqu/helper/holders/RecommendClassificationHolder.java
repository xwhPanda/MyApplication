package com.jiqu.helper.holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.NoScrollGridView;

/**
 * Created by xiongweihua on 2016/7/11.
 */
public class RecommendClassificationHolder extends BaseHolder{
    public LinearLayout typeLin;
    public TextView typeText;
    public View line1,line2;
    public NoScrollGridView classificationGridView;

    public RecommendClassificationHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView() {
        typeLin = (LinearLayout) itemView.findViewById(R.id.typeLin);
        typeText = (TextView) itemView.findViewById(R.id.typeText);
        line1 = (View) itemView.findViewById(R.id.line1);
        line2 = (View) itemView.findViewById(R.id.line2);
        classificationGridView = (NoScrollGridView) itemView.findViewById(R.id.classificationGridView);

        initViewSize();
    }

    private void initViewSize(){
        try {
            UIUtil.setTextSize(typeText,40);
            UIUtil.setViewSize(typeLin, MetricsTool.Rx * 220,MetricsTool.Rx * 80);
            UIUtil.setViewSizeMargin(typeLin,0,MetricsTool.Rx * 40,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
