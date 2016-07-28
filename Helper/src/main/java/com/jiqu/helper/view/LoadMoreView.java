package com.jiqu.helper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.R;

/**
 * Created by xiongweihua on 2016/7/8.
 */
public class LoadMoreView extends RelativeLayout{
    private TextView textView;

    public LoadMoreView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.home_choice_loadmore_layout,this);
        textView = (TextView) view.findViewById(R.id.loadmoreTip);
    }
}
