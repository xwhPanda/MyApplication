package com.jiqu.helper.holders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/20.
 */
public class RecommendPrerequisitesHolder extends BaseHolder{
    public LinearLayout tagLin;
    public TextView tagText;
    public RecyclerView recyclerView;

    public RecommendPrerequisitesHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView() {
        tagLin = (LinearLayout) itemView.findViewById(R.id.tagLin);
        tagText = (TextView) itemView.findViewById(R.id.tagText);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycleView);
        initViewSize();
        recyclerView.addItemDecoration(new SpaceItemDecoration(0,2));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(HelperApplication.context);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new RecommendAppAdapter(HelperApplication.context,R.layout.recommend_app_item_layout,null));

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewHeight(tagLin, MetricsTool.Rx * 50);
        UIUtil.setTextSize(tagText,30);
    }
}
