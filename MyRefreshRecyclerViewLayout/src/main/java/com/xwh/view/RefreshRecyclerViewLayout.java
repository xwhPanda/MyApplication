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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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
    private boolean mIsPullUp;
    private boolean mRefreshing;
    private boolean mHeadViewShowing;
    private boolean mFooterViewShowing;
    private boolean mLoading;
    private final int[] mParentOffsetInWindow = new int[2];
    //正在刷新的时候，如果往上滑，需要隐藏掉headview；此参数为开始滑动时headview的底部位置
    private float mRefreshingPushHeadBottom = 0;
    //正在加载更多的时候，向下滑动，隐藏footview；此参数为开始滑动时footview的顶部位置
    private float mLoadMoreFootViewTop = 0;
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
    private Animation animation;

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

    public void refreshFail(){

    }

    public void refreshCompleted(){
        mRefreshing = false;

        ViewCompat.offsetTopAndBottom(mContentView,-mHeadViewHeight);
        ViewCompat.offsetTopAndBottom(mHeadView,-mHeadViewHeight);
        invalidate();
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

    private void moveView(float distance){
        ViewCompat.offsetTopAndBottom(mHeadView,(int)distance);
        ViewCompat.offsetTopAndBottom(mContentView, (int) distance);
    }

    // NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i("TAG","NestedScrollingParent onStartNestedScroll");
        return isEnabled()
                && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.i("TAG","NestedScrollingParent onNestedScrollAccepted");
        // Reset the counter of how much leftover scroll needs to be consumed.
//        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        // Dispatch up to the nested parent
//        startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
        mTotalUnconsumed = 0;
//        mNestedScrollInProgress = true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.i("TAG","NestedScrollingParent onNestedPreScroll");
        //dy > 0:向上滑动；dy < 0:向下滑动
//        if (dy > 0 && canChildScrollUp() && Math.abs(mTotalUnconsumed) < mHeadViewHeight) {
        if (dy > 0){
//            if (mContentView.getTop() > 0 && !canChildScrollUp()){
//                if (dy > mContentView.getTop())
//                    dy = mContentView.getTop();
//                moveView(-dy);
//                consumed[1] = dy;
//            }
//            else if (!canChildScrollDown() && mContentView.getTop() > -mFooterViewHeight){
//                if (mContentView.getTop() - dy < -mFooterViewHeight)
//                    dy = mFooterViewHeight + mContentView.getTop();
//                ViewCompat.offsetTopAndBottom(mFooterView,-dy);
//                ViewCompat.offsetTopAndBottom(mContentView,-dy);
//                consumed[1] = dy;
//            }
        }
        else if(dy < 0){
//            if (!canChildScrollDown() && mContentView.getTop() < 0){
//                ViewCompat.offsetTopAndBottom(mFooterView,-dy);
//                ViewCompat.offsetTopAndBottom(mContentView,-dy);
//                consumed[1] = dy;
//
//                if (mContentView.getTop() > 0){
//                    ViewCompat.offsetTopAndBottom(mFooterView,-mContentView.getTop());
//                    ViewCompat.offsetTopAndBottom(mContentView,-mContentView.getTop());
//                }
//            }
        }

//        if (dy > 0 && mTotalUnconsumed > 0) {
//            if (dy > mTotalUnconsumed) {
//                consumed[1] = dy - (int) mTotalUnconsumed;
//                mTotalUnconsumed = 0;
//            } else {
//                mTotalUnconsumed -= dy;
//                consumed[1] = dy;
//            }
////            moveSpinner(mTotalUnconsumed);
//        }

        // If a client layout is using a custom start position for the circle
        // view, they mean to hide it again before scrolling the child view
        // If we get back to mTotalUnconsumed == 0 and there is more to go, hide
        // the circle so it isn't exposed if its blocking content is moved
//        if (mUsingCustomStart && dy > 0 && mTotalUnconsumed == 0
//                && Math.abs(dy - consumed[1]) > 0) {
//            mCircleView.setVisibility(View.GONE);
//        }

        // Now let our nested parent consume the leftovers
//        final int[] parentConsumed = mParentScrollConsumed;
//        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
//            consumed[0] += parentConsumed[0];
//            consumed[1] += parentConsumed[1];
//        }
    }


    @Override
    public int getNestedScrollAxes() {
        Log.i("TAG","NestedScrollingParent getNestedScrollAxes");
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
//        mNestedScrollInProgress = false;
        // Finish the spinner for nested scrolling if we ever consumed any
        // unconsumed nested scroll
        Log.i("TAG","NestedScrollingParent onStopNestedScroll");
//        if (mTotalUnconsumed > 0) {
////            finishSpinner(mTotalUnconsumed);
//            mTotalUnconsumed = 0;
//
//        }
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
        Log.i("TAG","NestedScrollingParent onNestedScroll");
//        final int dy = dyUnconsumed + mParentOffsetInWindow[1];
//        if (dy < 0 && !canChildScrollUp()) {
//            mTotalUnconsumed += Math.abs(dy);
////            moveSpinner(mTotalUnconsumed);
//        }
    }

    // NestedScrollingChild

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
        Log.i("TAG","NestedScrollingChild setNestedScrollingEnabled");
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.i("TAG","NestedScrollingChild isNestedScrollingEnabled");
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.i("TAG","NestedScrollingChild startNestedScroll");
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        Log.i("TAG","NestedScrollingChild stopNestedScroll");
        mNestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        Log.i("TAG","NestedScrollingChild hasNestedScrollingParent");
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        Log.i("TAG","NestedScrollingChild dispatchNestedScroll");
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.i("TAG","NestedScrollingChild dispatchNestedPreScroll");
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(
                dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX,
                                    float velocityY) {
        Log.i("TAG","NestedScrollingChild onNestedPreFling");
        return dispatchNestedPreFling(velocityX, velocityY);

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY,
                                 boolean consumed) {
        Log.i("TAG","NestedScrollingChild onNestedFling");
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.i("TAG","NestedScrollingChild dispatchNestedFling");
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.i("TAG","NestedScrollingChild dispatchNestedPreFling");
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }


    //判断mContentView在垂直向下是否可以滚动（是否到顶了）
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mContentView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mContentView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0
                        || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mContentView, -1) || mContentView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mContentView, -1);
        }
    }

    //判断mContentView在垂直向上是否可以滚动（是否到底了）
    public boolean canChildScrollDown() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mContentView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mContentView;
                return absListView.getChildCount() > 0
                        && (absListView.getLastVisiblePosition() < absListView.getChildCount()
                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getPaddingBottom());
            } else {
                return mContentView.getScrollY() < 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mContentView, 1);
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
        mFooterView.layout(childLeft,height,childLeft + mFooterViewWidth,height + mFooterViewHeight);
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
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                mIsPullUp = false;
                mInitialDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                if (y - mInitialDownY > 10 && !mFooterViewShowing && !mHeadViewShowing && !canChildScrollUp()){
                    Log.i("AA","------------3");
                    mIsBeingDragged = true;
                }else if(y - mInitialDownY < -10 && !mHeadViewShowing && !mFooterViewShowing && !canChildScrollDown()){
                    Log.i("AA","------------0");
                    mIsPullUp = true;
                }
                if ((mHeadViewShowing && y - mInitialDownY < 0)){
                    return true;
                }else if (mFooterViewShowing && y - mInitialDownY > 0){
                    Log.i("AA","------------1");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                mIsPullUp = false;
                break;
        }
        return mIsBeingDragged || mIsPullUp;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                mIsPullUp = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                if (mIsBeingDragged ){
                    startAlphaAnimation(y - mInitialDownY);
                    //下拉刷新
                    if ((y - mInitialDownY) > 0 && y - mInitialDownY < 2 * mHeadViewHeight){
                        mCurrentTargetOffsetTop = mContentView.getTop();
                        moveView(y - mInitialDownY - mCurrentTargetOffsetTop);
                        invalidate();
                    }
                }else if (mIsPullUp && mInitialDownY - y <= mFooterViewHeight && mInitialDownY - y > 0){
                    //上拉加载
                    ViewCompat.offsetTopAndBottom(mContentView,(int)(y - mInitialDownY - mContentView.getTop()));
                    ViewCompat.offsetTopAndBottom(mFooterView,(int)(y - mInitialDownY - mContentView.getTop()));
                    invalidate();
                }
                //隐藏刷新头部（正在刷新的时候向上滑）
                if (mHeadViewShowing && y - mInitialDownY > -mHeadViewHeight){
                    startAlphaAnimation((mHeadViewHeight + y - mInitialDownY));
                    if (mRefreshingPushHeadBottom == 0)
                        mRefreshingPushHeadBottom = mHeadView.getBottom();
                    moveView(y - mInitialDownY - mHeadView.getBottom() + mRefreshingPushHeadBottom);
                    invalidate();
                }

                if (mFooterViewShowing && y - mInitialDownY < mFooterViewHeight){
                    Log.i("AA","--------------2");
                    if (mLoadMoreFootViewTop == 0)
                        mLoadMoreFootViewTop = mFooterView.getTop();
                    ViewCompat.offsetTopAndBottom(mContentView,(int)(event.getY() - mInitialDownY + mLoadMoreFootViewTop - mFooterView.getTop()));
                    ViewCompat.offsetTopAndBottom(mFooterView,(int)(event.getY() - mInitialDownY + mLoadMoreFootViewTop - mFooterView.getTop()));
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged){
                    if (mHeadView.getBottom() <= mHeadViewHeight / 2){
                        //下拉的时候headview没有超过自身高度的一半，松开后弹回去
                        moveView(- mHeadView.getBottom());
                        mIsBeingDragged = false;
                        mHeadViewShowing = false;
                    }else {
                        mIsBeingDragged = false;
                        mHeadView.offsetTopAndBottom((int)( - mHeadView.getTop()));
                        mContentView.offsetTopAndBottom((int)(mHeadViewHeight - mContentView.getTop()));
                        mRefreshing = true;
                        mHeadViewShowing = true;
                        if (mOnRefreshListener != null)
                            mOnRefreshListener.startRefresh();
                    }
                    invalidate();

                }else if (mHeadViewShowing){
                    if (mHeadView.getBottom() <= mHeadViewHeight / 2){
                        //隐藏headview
                        moveView(- mHeadView.getBottom());
                        mHeadViewShowing = false;
                    }else {
                        mHeadView.offsetTopAndBottom((int)( - mHeadView.getTop()));
                        mContentView.offsetTopAndBottom((int)(mHeadViewHeight - mContentView.getTop()));
                        mHeadViewShowing = true;
                    }
                    invalidate();
                }
                if (mIsPullUp){
                    if ((mInitialDownY - event.getY()) < (mFooterViewHeight / 2)){
                        //上拉加载没有超过footview的1/2，弹回
                        mFooterViewShowing = false;
                        ViewCompat.offsetTopAndBottom(mContentView,(int)(mInitialDownY - event.getY()));
                        ViewCompat.offsetTopAndBottom(mFooterView,(int)(mInitialDownY - event.getY()));
                        invalidate();
                        Log.i("AA","CCCCCC");
                    }else {
                        //上拉加载超过footview的1/2，开始加载
                        if ((mInitialDownY - event.getY()) <= mFooterViewHeight){
                            Log.i("AA","AAAAAA");
                            ViewCompat.offsetTopAndBottom(mContentView,(int)(-mFooterViewHeight - event.getY() + mInitialDownY));
                            ViewCompat.offsetTopAndBottom(mFooterView,(int)(-mFooterViewHeight - event.getY() + mInitialDownY));
                        }else {
                            Log.i("AA","BBBBBB");
                        }
                        mFooterViewShowing = true;
                        invalidate();
                    }
                    mIsPullUp = false;
                }else if (mFooterViewShowing){
                    if (event.getY() - mInitialDownY < mFooterViewHeight / 2){
                        ViewCompat.offsetTopAndBottom(mFooterView,(int)(mInitialDownY - event.getY()));
                        ViewCompat.offsetTopAndBottom(mContentView,(int)(mInitialDownY - event.getY()));
                    }else {
                        if (event.getY() - mInitialDownY <= mFooterViewHeight){
                            Log.i("AA","aaaaaaa");
                            ViewCompat.offsetTopAndBottom(mFooterView,(int)(mFooterViewHeight + mInitialDownY - event.getY()));
                            ViewCompat.offsetTopAndBottom(mContentView,(int)(mFooterViewHeight + mInitialDownY - event.getY()));
                        }else {
                            Log.i("AA","bbbbbb : " + (int)(mFooterViewHeight + mInitialDownY - event.getY()));
                            ViewCompat.offsetTopAndBottom(mFooterView,(int)(mFooterViewHeight + mInitialDownY - event.getY()));
                            ViewCompat.offsetTopAndBottom(mContentView,(int)(mFooterViewHeight + mInitialDownY - event.getY()));
                        }
                        mFooterViewShowing = false;
                    }
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                return false;
        }
        return true;
    }

    //改变透明度
    private void startAlphaAnimation(final float y){
        animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mHeadView.setAlpha(y / mHeadViewHeight);
            }
        };
        animation.setDuration(300);
        mHeadView.clearAnimation();
        mHeadView.startAnimation(animation);
    }
}
