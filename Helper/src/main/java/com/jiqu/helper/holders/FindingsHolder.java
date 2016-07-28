package com.jiqu.helper.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/13.
 */
public class FindingsHolder extends BaseHolder{
    public SimpleDraweeView icon;
    public TextView name;
    public Button download;
    public TextView decs;
    public RelativeLayout detailRel;
    public SimpleDraweeView detailPic;
    public LinearLayout tagLin;
    public TextView tagText;
    public TextView fromText;
    public TextView fromWhere;
    public ImageView loveImg;
    public TextView loveText;

    public FindingsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView() {
        icon = (SimpleDraweeView) itemView.findViewById(R.id.icon);
        name = (TextView) itemView.findViewById(R.id.name);
        download = (Button) itemView.findViewById(R.id.download);
        decs = (TextView) itemView.findViewById(R.id.decs);
        detailRel = (RelativeLayout) itemView.findViewById(R.id.detailRel);
        detailPic = (SimpleDraweeView) itemView.findViewById(R.id.detailPic);
        tagLin = (LinearLayout) itemView.findViewById(R.id.tagLin);
        tagText = (TextView) itemView.findViewById(R.id.tagText);
        fromText = (TextView) itemView.findViewById(R.id.fromText);
        fromWhere = (TextView) itemView.findViewById(R.id.fromWhere);
        loveImg = (ImageView) itemView.findViewById(R.id.loveImg);
        loveText = (TextView) itemView.findViewById(R.id.loveText);

        initViewSize();
    }

    private void initViewSize(){
        try {
            UIUtil.setTextSize(name,55);
            UIUtil.setTextSize(decs,40);
            UIUtil.setTextSize(tagText,30);
            UIUtil.setTextSize(fromText,35);
            UIUtil.setTextSize(fromWhere,35);
            UIUtil.setTextSize(loveText,35);

            UIUtil.setViewSize(icon, MetricsTool.Rx * 110,MetricsTool.Rx * 110);
            UIUtil.setViewHeight(detailPic,MetricsTool.Rx * 475);
            UIUtil.setViewSize(download, MetricsTool.Rx * 114,MetricsTool.Rx * 90);
            UIUtil.setViewSize(tagLin,MetricsTool.Rx * 150,MetricsTool.Rx * 54);

            UIUtil.setViewSizeMargin(icon,MetricsTool.Rx * 50,MetricsTool.Rx * 40,MetricsTool.Rx * 30,MetricsTool.Rx * 40);
            UIUtil.setViewSizeMargin(decs,MetricsTool.Rx * 15,0,MetricsTool.Rx * 15,0);
            UIUtil.setViewSizeMargin(download,0,0,MetricsTool.Rx * 30,0);
            UIUtil.setViewSizeMargin(fromText,MetricsTool.Rx * 50,0,0,0);
            UIUtil.setViewSizeMargin(fromWhere,MetricsTool.Rx * 30,0,0,0);
            UIUtil.setViewSizeMargin(loveImg,MetricsTool.Rx * 800,MetricsTool.Rx * 40,0,MetricsTool.Rx * 40);
            UIUtil.setViewSizeMargin(loveText,MetricsTool.Rx * 10,0,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}