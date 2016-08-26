package com.jiqu.helper.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.data.SearchData;
import com.jiqu.helper.holders.BaseHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/22.
 */
public class SearchAdapter extends BaseAdapter{

    public SearchAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new SearchHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, int position) {
        SearchData.SearchInfo searchInfo = (SearchData.SearchInfo) mDatas.get(position);
        ((TextView)baseHolder.getView(R.id.name)).setText(searchInfo.getContent());
    }

    public class SearchHolder extends BaseHolder{

        public SearchHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
        }
    }
}
