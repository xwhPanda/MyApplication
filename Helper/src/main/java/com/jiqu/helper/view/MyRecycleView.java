package com.jiqu.helper.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;

import java.util.ArrayList;

/**
 * Created by xiongweihua on 2016/8/5.
 */
public class MyRecycleView extends RecyclerView{
    private boolean isLoadingData = false;
    private Context mContext;
    private OnLoadDataListener onLoadDataListener;
    private ArrayList<View> mHeadViews = new ArrayList<>();
    private ArrayList<View> mFootViews = new ArrayList<>();
    private boolean canPullDown = false;
    private boolean canPullUp = false;
    private ViewGroup.LayoutParams footerParamstVisible;
    private View footerView;
    private Adapter mAdapter;

    public MyRecycleView(Context context) {
        super(context);
        init(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        setHasFixedSize(true);
        if (mAdapter != null && ((WrapAdapter)mAdapter).getFootersCount() > 0){
            /** 先隐藏foot **/
            mFootViews.get(0).setVisibility(GONE);
        }
    }

    private void createFooterView(){
            LinearLayout footerLayout = new LinearLayout(mContext);
            footerLayout.setGravity(Gravity.CENTER);
        if (footerParamstVisible == null){
           footerParamstVisible = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
            footerLayout.setLayoutParams(footerParamstVisible);
        TextView text = new TextView(mContext);
        text.setText("正在加载...");
        UIUtil.setTextSize(text,40);
        footerLayout.addView(text);
        footerView = footerLayout;
        mFootViews.add(footerLayout);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        createFooterView();
        if (mHeadViews.isEmpty() && mFootViews.isEmpty()){
            mAdapter = adapter;
            super.setAdapter(adapter);
        }else {
            mAdapter = new WrapAdapter(mHeadViews,mFootViews,adapter);
            if (mAdapter != null && ((WrapAdapter)mAdapter).getFootersCount() > 0){
                /** 先隐藏foot **/
                mFootViews.get(0).setVisibility(GONE);
            }
            super.setAdapter(mAdapter);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        /** 不是正在滚动且不是正在加载数据 **/
        if (state == RecyclerView.SCROLL_STATE_IDLE && !isLoadingData){
            LayoutManager manager = getLayoutManager();
            int lastVisibleItemPosition = 0;
            /** 暂时是垂直的recycleview，当listview使用 **/
            if (manager instanceof LinearLayoutManager){
                lastVisibleItemPosition = ((LinearLayoutManager)manager).findLastVisibleItemPosition();
            }
            if (manager.getChildCount() > 0 && (lastVisibleItemPosition >= manager.getItemCount() - 1)){
                mFootViews.clear();
                mFootViews.add(footerView);
                if (mFootViews.size() > 0){
                    ((LinearLayout)mFootViews.get(0)).setGravity(Gravity.CENTER);
                    mFootViews.get(0).setVisibility(VISIBLE);
                    mAdapter.notifyDataSetChanged();
                }
                /** 加载更多 **/
                if (onLoadDataListener != null){
                    isLoadingData = true;
                    onLoadDataListener.onLoadMore();
                }
            }else {
            }
        }
    }

    public void loadComplete(){
        isLoadingData = false;
        if (mFootViews.size() > 0){
            if (mFootViews.get(0) != null){
                mFootViews.remove(0);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void cancelLoad(){
        loadComplete();
    }

    public void addHeadView(View view){
        mHeadViews.add(view);
    }

    public void addFootView(View view){
        mFootViews.clear();
        view.setVisibility(GONE);
        mFootViews.add(view);
    }

    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener){
        this.onLoadDataListener = onLoadDataListener;
    }

    public interface OnLoadDataListener{
        void onLoadMore();
    }

    class WrapAdapter extends Adapter {
        private Adapter mAdapter;
        private final int HEADER_TYPE = 1;
        private final int CONTENT_TYPE = 2;
        private final int FOOTER_TYPE = 3;
        private ArrayList<View> mHeaderViews;
        private ArrayList<View> mFooterViews;
        final ArrayList<View> EMPTY_INFO_LIST = new ArrayList<>();

        public WrapAdapter(ArrayList<View> mHeaderViews, ArrayList<View> mFooterViews, Adapter adapter) {
            this.mAdapter = adapter;
            if (mHeaderViews == null) {
                this.mHeaderViews = EMPTY_INFO_LIST;
            } else {
                this.mHeaderViews = mHeaderViews;
            }
            if (mFooterViews == null) {
                this.mFooterViews = EMPTY_INFO_LIST;
            } else {
                this.mFooterViews = mFooterViews;
            }
        }

        public int getHeadersCount() {
            return mHeaderViews.size();
        }

        public int getFootersCount() {
            return mFooterViews.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEADER_TYPE) {
                return new HeaderViewHolder(mHeaderViews.get(0));
            } else if (viewType == FOOTER_TYPE) {
                return new HeaderViewHolder(mFooterViews.get(0));
            } else {
                return mAdapter.onCreateViewHolder(parent, viewType);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int numHeaders = getHeadersCount();
            if (position < numHeaders) {
                return;
            }
            int index = position - numHeaders;
            int contentCount;
            if (mAdapter != null) {
                contentCount = mAdapter.getItemCount();
                if (index < contentCount) {
                    mAdapter.onBindViewHolder(holder, index);
                }
            }
        }

        @Override
        public int getItemCount() {
            if (mAdapter != null) {
                return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
            } else {
                return getHeadersCount() + getFootersCount();
            }
        }

        @Override
        public int getItemViewType(int position) {
            int numHeaders = getHeadersCount();
            if (position < numHeaders)
                return HEADER_TYPE;
            int index = position - numHeaders;
            int contentCount;
            if (mAdapter != null) {
                contentCount = mAdapter.getItemCount();
                if (index < contentCount)
                    return CONTENT_TYPE;
            }
            return FOOTER_TYPE;
        }

        @Override
        public long getItemId(int position) {
            int numHeaders = getHeadersCount();
            if (mAdapter != null && position >= numHeaders) {
                int index = position - numHeaders;
                int contentCount = mAdapter.getItemCount();
                if (index < contentCount) {
                    return mAdapter.getItemId(index);
                }
            }
            return -1;
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            /** 必须要加，可能会导致第一次加载数据的时候显示不出来 **/
            mAdapter.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            mAdapter.unregisterAdapterDataObserver(observer);
        }
    }
        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }
}
