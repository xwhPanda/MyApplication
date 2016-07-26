package com.jiqu.helper.holders;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by xiongweihua on 2016/7/22.
 */
public abstract class BaseHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View mContentView;

    public BaseHolder(View itemView) {
        super(itemView);
        mContentView = itemView;
        views = new SparseArray<>();
        initView();
    }

    public abstract void initView();

    public View getmContentView(){
        return mContentView;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view == null){
            view = mContentView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }
}
