package com.jiqu.helper.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiongweihua on 2016/7/7.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int horizontalSpace,verticalSpace;

    public SpaceItemDecoration(int horizontalSpace,int verticalSpace){
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = horizontalSpace;
    }

    public SpaceItemDecoration(float horizontalSpace,float verticalSpace){
        this.horizontalSpace = (int)horizontalSpace;
        this.verticalSpace = (int)verticalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) != 0){
            outRect.left = verticalSpace;
            outRect.top = horizontalSpace;
        }

    }
}
