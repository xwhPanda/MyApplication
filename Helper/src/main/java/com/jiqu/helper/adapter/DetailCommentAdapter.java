package com.jiqu.helper.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.data.CommentData;
import com.jiqu.helper.holders.BaseHolder;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/18.
 */
public class DetailCommentAdapter extends BaseAdapter{
    public DetailCommentAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new DetailCommentHolder(view);
    }

    @Override
    public void convert(BaseHolder baseHolder, int position) {
        CommentData.CommentInfo commentInfo = (CommentData.CommentInfo) mDatas.get(position);
        ((SimpleDraweeView)baseHolder.getView(R.id.icon)).setImageURI(Uri.parse(commentInfo.getPhoto()));
        ((TextView)baseHolder.getView(R.id.name)).setText(commentInfo.getUsername());
        ((TextView)baseHolder.getView(R.id.time)).setText(commentInfo.getTime());
        ((TextView)baseHolder.getView(R.id.comment)).setText(commentInfo.getContent());
    }

    public class DetailCommentHolder extends BaseHolder{

        public DetailCommentHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {

        }
    }
}
