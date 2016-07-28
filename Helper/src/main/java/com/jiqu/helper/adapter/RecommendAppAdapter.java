package com.jiqu.helper.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.RecommendAppHolder;
import com.jiqu.helper.tools.Tools;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/7.
 */
public class RecommendAppAdapter extends BaseAdapter {

    public RecommendAppAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new RecommendAppHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, Object object) {
        if (object != null){
            GameInfo info = (GameInfo)object;
            Uri uri = Uri.parse(info.getIcon());
            ((SimpleDraweeView) baseHolder.getView(R.id.gameIcon)).setImageURI(uri);
            ((TextView) baseHolder.getView(R.id.gameName)).setText(info.getApply_name());
            ((TextView) baseHolder.getView(R.id.downloadCount)).setText(info.getDown());
            ((TextView) baseHolder.getView(R.id.gameSize)).setText(info.getSize() + "");
        }
    }
}