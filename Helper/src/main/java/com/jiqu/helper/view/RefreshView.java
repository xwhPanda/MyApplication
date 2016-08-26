package com.jiqu.helper.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/28.
 */
public class RefreshView extends LinearLayout {
    private ImageView image;
    private Button loadAgain;

    public RefreshView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.refresh_view_layout, this);
        image = (ImageView) view.findViewById(R.id.image);
        loadAgain = (Button) view.findViewById(R.id.loadAgain);

        image.setImageResource(R.drawable.loading_animation);
        AnimationDrawable drawable = (AnimationDrawable) image.getDrawable();
        drawable.start();


        initViewSize();
    }

    private void initViewSize() {
        UIUtil.setViewSize(image, MetricsTool.Rx * 486, MetricsTool.Rx * 486);
        UIUtil.setViewSize(loadAgain, MetricsTool.Rx * 486, MetricsTool.Rx * 84);

        try {
            UIUtil.setViewSizeMargin(loadAgain, 0, MetricsTool.Rx * 120, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAgainLisenter(OnClickListener listener){
        loadAgain.setOnClickListener(listener);
    }

    public void loadFail() {
        loadAgain.setVisibility(VISIBLE);
        image.clearAnimation();
        image.setImageResource(R.drawable.load_fail_animation);
        AnimationDrawable drawable = (AnimationDrawable) image.getDrawable();
        drawable.start();
    }

    public void loadAgain() {
        loadAgain.setVisibility(GONE);
        image.clearAnimation();
        image.setImageResource(R.drawable.loading_animation);
        AnimationDrawable drawable = (AnimationDrawable) image.getDrawable();
        drawable.start();
    }
}