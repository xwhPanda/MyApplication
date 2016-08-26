package com.jiqu.helper.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/29.
 */
public class TitleBar extends LinearLayout{
    private LinearLayout parent;
    private LinearLayout backLin,shareLin;
    private ImageView backImg,shareImg;
    private TextView title;
    private Activity mActivity;
    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar_layout,this);
        parent = (LinearLayout) view.findViewById(R.id.parent);
        backLin = (LinearLayout) view.findViewById(R.id.backLin);
        shareLin = (LinearLayout) view.findViewById(R.id.shareLin);
        backImg = (ImageView) view.findViewById(R.id.backImg);
        shareImg = (ImageView) view.findViewById(R.id.shareImg);
        title = (TextView) view.findViewById(R.id.title);

        backLin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActivity != null)
                    mActivity.finish();
            }
        });

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewSize(backLin, MetricsTool.Rx * 120,MetricsTool.Rx * 100);
        UIUtil.setViewSize(backImg,MetricsTool.Rx * 48,MetricsTool.Rx * 48);
        UIUtil.setViewSize(shareImg,MetricsTool.Rx * 100,MetricsTool.Rx * 100);
        UIUtil.setTextSize(title,50);
        try {
            UIUtil.setViewSizeMargin(backLin,MetricsTool.Rx * 30 ,MetricsTool.Rx * 35,0,MetricsTool.Rx * 35);
            UIUtil.setViewSizeMargin(shareLin,0 ,0,MetricsTool.Rx * 60,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBackgroundColor(int color){
        parent.setBackgroundColor(color);
    }

    public void setTitle(String string){
        title.setText(string);
    }

    public void setActivity(Activity activity){
        mActivity = activity;
    }

    public void setShareVisible(int visible){
        shareLin.setVisibility(visible);
    }

    public void setShareListener(OnClickListener listener){
        shareLin.setOnClickListener(listener);
    }
}
