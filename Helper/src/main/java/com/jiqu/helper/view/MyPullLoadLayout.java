package com.jiqu.helper.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;

/**
 * Created by xiongweihua on 2016/8/5.
 */
public class MyPullLoadLayout extends LinearLayout {
    private RecyclerView recyclerView;
    private TextView loadMore;

    public MyPullLoadLayout(Context context) {
        super(context);
    }

    public MyPullLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyPullLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.pull_load_layout,null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        loadMore = (TextView)view.findViewById(R.id.loadMoreView);
        loadMore.setVisibility(GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setScrollbarFadingEnabled(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }



    public interface CanPullable{
        /**
         * 判断是否可以下拉，如果不需要下拉功能可以直接return false
         *
         * @return true如果可以下拉否则返回false
         */
        boolean canPullDown();

        /**
         * 判断是否可以上拉，如果不需要上拉功能可以直接return false
         *
         * @return true如果可以上拉否则返回false
         */
        boolean canPullUp();
    }
}
