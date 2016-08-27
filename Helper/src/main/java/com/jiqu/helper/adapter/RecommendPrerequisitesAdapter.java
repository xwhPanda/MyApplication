package com.jiqu.helper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.data.RecommendPrerequisitesData;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendPrerequisitesHolder;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.tools.Tools;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/21.
 */
public class RecommendPrerequisitesAdapter extends BaseAdapter {
    private Context context;

    public RecommendPrerequisitesAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
        this.context = context;
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendPrerequisitesHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, int position) {
        final RecommendPrerequisitesData.RecommendPrerequisitesInfo info = (RecommendPrerequisitesData.RecommendPrerequisitesInfo) mDatas.get(position);
        RecommendAppAdapter appAdapter = new RecommendAppAdapter(context, R.layout.recommend_app_item_layout, info.getCol());
        ((TextView) baseHolder.getView(R.id.tagText)).setText(info.getName());
        baseHolder.getView(R.id.tagLin).setBackgroundResource(R.mipmap.prerequisites_blue);
        RecyclerView recyclerView = (RecyclerView) baseHolder.getView(R.id.recycleView);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2, 0));
        recyclerView.setHasFixedSize(true);
        appAdapter.setListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseAdapter adapter, int position) {
                Intent intent = new Intent(context, DetailActivity.class);
                if (Tools.getType(info.getCol().get(position).getType()) == -1) {
                    return;
                } else {
                    intent.putExtra("type", Tools.getType(info.getCol().get(position).getType()));
                }
                intent.putExtra("id", info.getCol().get(position).getId());
                intent.putExtra("name", info.getCol().get(position).getApply_name());
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(appAdapter);
    }
}
