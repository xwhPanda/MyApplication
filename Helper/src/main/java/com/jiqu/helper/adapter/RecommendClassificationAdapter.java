package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendClassificationHolder;
import com.jiqu.helper.interfaces.ClassificationGridViewItemClickListener;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/11.
 */
public class RecommendClassificationAdapter extends BaseAdapter {
    private Context context;
    private List<String> title;
    private ClassificationGridViewItemClickListener itemClickListener;

    public RecommendClassificationAdapter(Context context, int viewId, List datas, List<String> title) {
        super(context, viewId, datas);
        this.context = context;
        this.title = title;
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendClassificationHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, final int position) {
        List<RecommendClassificationItemData> data = (List<RecommendClassificationItemData>) mDatas.get(position);
        GridView gridView = baseHolder.getView(R.id.classificationGridView);
        RecommendClassificationItemAdapter adapter = new RecommendClassificationItemAdapter(context, data);
        ((TextView) baseHolder.getView(R.id.typeText)).setText(title.get(position));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(adapterView,view,i,position);
            }
        });
    }

    public void setOnItemClickListener(ClassificationGridViewItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
