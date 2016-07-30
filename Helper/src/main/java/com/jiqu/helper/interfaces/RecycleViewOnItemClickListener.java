package com.jiqu.helper.interfaces;

import android.view.View;

import com.jiqu.helper.adapter.BaseAdapter;

/**
 * Created by xiongweihua on 2016/7/25.
 */
public interface RecycleViewOnItemClickListener {
    public void onItemClick(View view, BaseAdapter adapter,int position);
}
