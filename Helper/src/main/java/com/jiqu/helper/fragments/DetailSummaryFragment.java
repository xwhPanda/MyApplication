package com.jiqu.helper.fragments;

import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/8.
 */
public class DetailSummaryFragment extends BaseFragment{
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout contentLin;
    private ImageView descriptionImg;
    private TextView descriptionText;
    private TextView description;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.detail_summary_layout,null);
        return view;
    }

    @Override
    public void initView() {
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView);
        contentLin = (LinearLayout) view.findViewById(R.id.contentLin);
        descriptionImg = (ImageView) view.findViewById(R.id.descriptionImg);
        descriptionText = (TextView) view.findViewById(R.id.descriptionText);
        description = (TextView) view.findViewById(R.id.description);
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewSize(descriptionImg,Rx * 84 , Rx * 84);
        UIUtil.setViewSize(horizontalScrollView, Rx * 1080,Rx * 800);
        UIUtil.setTextSize(descriptionText,40);
        UIUtil.setTextSize(description,30);
    }

    @Override
    public void initData() {

    }

    public void setData(List<String> urls){
        int size = urls.size();
        for (int i = 0;i < size;i++){
            SimpleDraweeView imageView = new SimpleDraweeView(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 480),(int)(MetricsTool.Rx * 800));
            params.topMargin = (int)(MetricsTool.Rx * 10);
            if (i != 0){
                params.leftMargin = (int)(MetricsTool.Rx * 10);
            }
            params.gravity = Gravity.CENTER;
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageURI(Uri.parse(urls.get(i)));
            contentLin.addView(imageView);
        }
    }
}
