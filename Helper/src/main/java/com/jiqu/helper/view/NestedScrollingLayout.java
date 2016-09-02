package com.jiqu.helper.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by xiongweihua on 2016/8/4.
 */
public class NestedScrollingLayout extends LinearLayout implements NestedScrollingParent{
    private int headHeight = 0;
    private OverScroller mScroller;

    public NestedScrollingLayout(Context context) {
        super(context);
        initView(context);
    }

    public NestedScrollingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NestedScrollingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setHeadHeight(int headHeight){
        this.headHeight = headHeight;
    }

    private void initView(Context context){
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
    }

    @Override
    public void onStopNestedScroll(View child) {
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY() >= headHeight)
            return false;
        fling((int)velocityY);
        return true;
    }



    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //dy > 0:向上滑动；dy < 0:向下滑动
        boolean hiddenTop = dy > 0 && getScrollY() < headHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop)
        {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    public void fling(int velocityY)
    {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, headHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0){
            y = 0;
        }
        if (y > headHeight){
            y = headHeight;
        }
        if (y != getScrollY()){
            super.scrollTo(x, y);
        }
    }

        @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
