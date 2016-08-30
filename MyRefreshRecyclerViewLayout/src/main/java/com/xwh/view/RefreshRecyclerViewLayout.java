package com.xwh.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.xwh.interfaces.OnRefreshListener;

/**
 * Created by xiongweihua on 2016/8/27.
 */
public class RefreshRecyclerViewLayout extends LinearLayout implements NestedScrollingChild,NestedScrollingParent{
    private OnRefreshListener mOnRefreshListener;
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final int[] mParentScrollConsumed = new int[2];
    private float mTotalUnconsumed;
    private float mInitialDownY;
    private boolean mIsBeingDragged;
    private boolean mRefreshing;
    //正在刷新的时候，如果往上滑，需要隐藏掉headview；此参数为开始滑动时headview的底部位置
    private float mRefreshingPushHeadBottom = 0;
    //下拉刷新在垂直方向的距离
    private int mCurrentTargetOffsetTop;
    //下拉刷新view
    private View mHeadView;
    private int mHeadViewWidth;
    private int mHeadViewHeight;
    //显示数据的view（RecyclerView或ListView）
    private View mContentView;
    //上拉加载view
    private View mFooterView;
    private int mFooterViewWidth;
    private int mFooterViewHeight;

    public RefreshRecyclerViewLayout(Context context) {
        super(context);
    }

    public RefreshRecyclerViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    public RefreshRecyclerViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        this.mOnRefreshListener = onRefreshListener;
    }

    private void initView(){
        mHeadView = getChildAt(0);
        mContentView = getChildAt(1);
        mFooterView = getChildAt(2);

        mHeadViewWidth = mHeadView.getMeasuredWidth();
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        mFooterViewWidth = mFooterView.getMeasuredWidth();
        mFooterViewHeight = mFooterView.getMeasuredHeight();
    }

    // NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return isEnabled() && !mRefreshing
                && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        // Reset the counter of how much leftover scroll needs to be consumed.
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        // Dispatch up to the nested parent
        startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
        mTotalUnconsumed = 0;
//        mNestedScrollInProgress = true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // If we are in the middle of consuming, a scroll, then we want to move the spinner back up
        // before allowing the list to scroll
        if (dy > 0 && mTotalUnconsumed > 0) {
            if (dy > mTotalUnconsumed) {
                consumed[1] = dy - (int) mTotalUnconsumed;
                mTotalUnconsumed = 0;
            } else {
                mTotalUnconsumed -= dy;
                consumed[1] = dy;
            }
//            moveSpinner(mTotalUnconsumed);
        }

        // If a client layout is using a custom start position for the circle
        // view, they mean to hide it again before scrolling the child view
        // If we get back to mTotalUnconsumed == 0 and there is more to go, hide
        // the circle so it isn't exposed if its blocking content is moved
//        if (mUsingCustomStart && dy > 0 && mTotalUnconsumed == 0
//                && Math.abs(dy - consumed[1]) > 0) {
//            mCircleView.setVisibility(View.GONE);
//        }

        // Now let our nested parent consume the leftovers
        final int[] parentConsumed = mParentScrollConsumed;
        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
            consumed[0] += parentConsumed[0];
            consumed[1] += parentConsumed[1];
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
//        mNestedScrollInProgress = false;
        // Finish the spinner for nested scrolling if we ever consumed any
        // unconsumed nested scroll
        if (mTotalUnconsumed > 0) {
//            finishSpinner(mTotalUnconsumed);
            mTotalUnconsumed = 0;
        }
        // Dispatch up our nested parent
        stopNestedScroll();
    }

    @Override
    public void onNestedScroll(final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        // Dispatch up to the nested parent first
//        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,mParentOffsetInWindow);

        // This is a bit of a hack. Nested scrolling works from the bottom up, and as we are
        // sometimes between two nested scrolling views, we need a way to be able to know when any
        // nested scrolling parent has stopped handling events. We do that by using the
        // 'offset in window 'functionality to see if we have been moved from the event.
        // This is a decent indication of whether we should take over the event stream or not.
//        final int dy = dyUnconsumed + mParentOffsetInWindow[1];
//        if (dy < 0 && !canChildScrollUp()) {
//            mTotalUnconsumed += Math.abs(dy);
//            moveSpinner(mTotalUnconsumed);
//        }
    }

    // NestedScrollingChild

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(
                dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX,
                                    float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY,
                                 boolean consumed) {
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }


    //判断mContentView在垂直方向上是否可以滚动
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mContentView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mContentView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mContentView, -1) || mContentView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mContentView, -1);
        }
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (mContentView == null)
            initView();
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        final int childTop = getPaddingTop();
        final int childLeft = getPaddingLeft();
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();
        if (getChildCount() == 0 || mContentView == null)
            return;
        mContentView.layout(childLeft,childTop + mCurrentTargetOffsetTop,childLeft + childWidth,childTop + childHeight + mCurrentTargetOffsetTop);
        mHeadView.layout(childLeft,-mHeadViewHeight,childLeft + mHeadViewWidth,mCurrentTargetOffsetTop);
        mFooterView.layout(childLeft,height - mFooterViewHeight,childLeft + mFooterViewWidth,height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mContentView == null)
            return;
//        mContentView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),MeasureSpec.EXACTLY)
//            ,MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingTop(),MeasureSpec.EXACTLY));
//        mHeadView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),MeasureSpec.EXACTLY)
//                ,MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingTop(),MeasureSpec.EXACTLY));
//        mFooterView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),MeasureSpec.EXACTLY)
//                ,MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingTop(),MeasureSpec.EXACTLY));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (canChildScrollUp())
            return false;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                mInitialDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                if ((y - mInitialDownY > 10 && !mIsBeingDragged && !mRefreshing)){
                    mIsBeingDragged = true;
                }
                if ((mRefreshing && y - mInitialDownY < 0)){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                break;
        }
        return mIsBeingDragged;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        if (canChildScrollUp())
            return false;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();

                if (mIsBeingDragged){
                    mCurrentTargetOffsetTop = mContentView.getTop();
                    mHeadView.offsetTopAndBottom((int)(y - mInitialDownY - mCurrentTargetOffsetTop));
                    mContentView.offsetTopAndBottom((int)(y - mInitialDownY - mCurrentTargetOffsetTop));
                    invalidate();
                }else {
                    if (mRefreshing){
                        if (y - mInitialDownY < 0 && mContentView.getTop() > 0){
                            if (mRefreshingPushHeadBottom == 0)
                                mRefreshingPushHeadBottom = mHeadView.getBottom();
                            mContentView.offsetTopAndBottom((int)(y - mInitialDownY + mRefreshingPushHeadBottom - mHeadView.getBottom()));
                            mHeadView.offsetTopAndBottom((int)(y - mInitialDownY + mRefreshingPushHeadBottom - mHeadView.getBottom()));
                            invalidate();
                        }else if (mHeadView.getTop() <= -mHeadViewHeight){
//                            mRefreshing = false;
                            return false;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged && !mRefreshing){
                    if (mHeadView.getBottom() <= mHeadViewHeight / 2){
                        mHeadView.offsetTopAndBottom((int)( - mHeadView.getBottom()));
                        mContentView.offsetTopAndBottom((int)(- mContentView.getTop()));
                    }else {
                        mIsBeingDragged = false;
                        mHeadView.offsetTopAndBottom((int)( - mHeadView.getTop()));
                        mContentView.offsetTopAndBottom((int)(mHeadViewHeight - mContentView.getTop()));
                        mRefreshing = true;
                    }
                    invalidate();

                }else if (mRefreshing){
                    mRefreshing = false;
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                return false;
        }
        return true;
    }
}
