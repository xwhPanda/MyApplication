package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendPrerequisitesHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/21.
 */
public class RecommendPrerequisitesAdapter extends BaseAdapter{

    public RecommendPrerequisitesAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendPrerequisitesHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, Object o) {
        ((TextView)baseHolder.getView(R.id.tagText)).setText("交友聊天");
        baseHolder.getView(R.id.tagLin).setBackgroundResource(R.mipmap.prerequisites_blue);
    }
}
