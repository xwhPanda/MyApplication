package com.jiqu.helper.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.RecommendData;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendRecycleHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/7.
 */
public class RecommendRecycleAdapter extends BaseAdapter{

    public RecommendRecycleAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendRecycleHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, int position) {
            GameInfo gameInfo = (GameInfo) mDatas.get(position);
            Uri uri = Uri.parse(gameInfo.getIcon());
            ((SimpleDraweeView)baseHolder.getView(R.id.icon)).setImageURI(uri);
    }
}
