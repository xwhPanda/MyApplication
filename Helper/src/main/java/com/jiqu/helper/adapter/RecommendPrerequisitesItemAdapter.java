package com.jiqu.helper.adapter;

import android.content.Context;
import android.view.View;

import com.jiqu.helper.holders.BaseHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/25.
 */
public class RecommendPrerequisitesItemAdapter extends BaseAdapter{

    public RecommendPrerequisitesItemAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return null;
    }

    @Override
    public void convert(BaseHolder baseHolder, Object o) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
