package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendClassificationHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/11.
 */
public class RecommendClassificationAdapter extends BaseAdapter{
    private Context context;

    public RecommendClassificationAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
        this.context = context;
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendClassificationHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, Object object) {
        if (object != null){
            RecommendClassificationItemData data = (RecommendClassificationItemData)object;
            RecommendClassificationItemAdapter adapter = new RecommendClassificationItemAdapter(context,data.getCol());
            ((TextView)baseHolder.getView(R.id.typeText)).setText(data.getName());
            ((GridView)baseHolder.getView(R.id.classificationGridView)).setAdapter(adapter);
        }
    }
}
