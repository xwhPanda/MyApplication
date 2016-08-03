package com.jiqu.helper.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.RecommendFindingsItemInfo;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.FindingsHolder;
import com.jiqu.helper.tools.Tools;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/13.
 */
public class FindingsAdapter extends BaseAdapter {

    public FindingsAdapter(Context context, int viewId, List data) {
        super(context, viewId, data);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new FindingsHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, int position) {
            RecommendFindingsItemInfo info = (RecommendFindingsItemInfo) mDatas.get(position);
            Uri iconUri = Uri.parse(info.getIcon());
            ((SimpleDraweeView) baseHolder.getView(R.id.icon)).setImageURI(iconUri);
            ((TextView)baseHolder.getView(R.id.name)).setText(info.getApply_name());
            ((TextView)baseHolder.getView(R.id.decs)).setText(info.getRotate_intro());
            Uri detailUri = Uri.parse(info.getRotate().getRotate_pic());
            ((SimpleDraweeView) baseHolder.getView(R.id.detailPic)).setImageURI(detailUri);
            ((TextView)baseHolder.getView(R.id.fromWhere)).setText(info.getRotate().getFrom());
            ((TextView)baseHolder.getView(R.id.loveText)).setText(info.getRotate().getFavorite() + Tools.getString(R.string.love_number));
    }
}
