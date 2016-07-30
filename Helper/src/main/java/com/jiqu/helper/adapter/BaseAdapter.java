package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/22.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder>{
    private Context context;
    private List<T> mDatas;
    private int mViewId;
    private RecycleViewOnItemClickListener listener;
    private BaseAdapter adapter;

    public BaseAdapter(Context context,int viewId,List<T> datas){
        this.context = context;
        mViewId = viewId;
        mDatas = datas;
        adapter = this;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(HelperApplication.context).inflate(mViewId,parent,false);
        return getHolder(view);
    }

    @Override
    public void onViewRecycled(BaseHolder holder) {
        super.onViewRecycled(holder);
    }

    public abstract BaseHolder getHolder(View view);
    public abstract void convert(BaseHolder baseHolder, T t);

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        if (mDatas == null || mDatas.size() <= 0){
            convert(holder,null);
        }else {
            convert(holder, mDatas.get(position));
        }
        if (listener != null){
            holder.getmContentView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view,adapter,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null)
            return 0;
        return mDatas.size();
    }

    public void setListener(RecycleViewOnItemClickListener listener){
        this.listener = listener;
    }
}
