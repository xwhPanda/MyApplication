package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.helper.R;
import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.FootHoler;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/22.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder>{
    private final int TYPE_HEAD = 1;
    private final int TYPE_CONTENT = 2;
    private final int TYPE_ROOT = 3;
    private Context context;
    public List<T> mDatas;
    private int mViewId;
    private RecycleViewOnItemClickListener listener;
    private BaseAdapter adapter;

    public BaseAdapter(Context context,int viewId,List<T> datas){
        this.context = context;
        mViewId = viewId;
        mDatas = datas;
        adapter = this;
    }

    public void setmDatas(List<T> datas){
        if (mDatas != null){
            mDatas = datas;
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        View view = LayoutInflater.from(HelperApplication.context).inflate(mViewId,parent,false);
        holder = getHolder(view);
        return holder;
    }

    @Override
    public void onViewRecycled(BaseHolder holder) {
        super.onViewRecycled(holder);
    }

    public abstract BaseHolder getHolder(View view);
    public abstract void convert(BaseHolder baseHolder,int position);

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
                convert(holder,position);
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
        return mDatas.size();
    }


    public void setListener(RecycleViewOnItemClickListener listener){
        this.listener = listener;
    }
}