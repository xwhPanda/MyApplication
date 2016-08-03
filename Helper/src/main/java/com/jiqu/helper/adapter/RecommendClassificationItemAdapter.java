package com.jiqu.helper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiqu.helper.data.RecommendClassificationInfo;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.holders.RecommendClassificationItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/11.
 */
public class RecommendClassificationItemAdapter extends BaseAdapter{
    private Context context;
    private List<RecommendClassificationItemData> list;

    public RecommendClassificationItemAdapter(Context context,List<RecommendClassificationItemData> infoList){
        this.context = context;
        list = infoList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecommendClassificationItemHolder holder;
        if (view == null){
            holder = new RecommendClassificationItemHolder(context);
            view = holder.getRootView();
            view.setTag(holder);
        }else {
            holder = (RecommendClassificationItemHolder) view.getTag();
        }
        holder.setData(list.get(i));
        return view;
    }
}
