package com.jiqu.helper.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	private ViewGroup viewGroup;
	private View viewOne;
	private View viewTwo;
	private View viewThree;
	private boolean isFirst = true;
	private int hegihtDist;
	private float pullDownY,pullUpY;
	private float y;
	private float lastY;
	private boolean canPullDown = false;
	private boolean canPullUp = true;

	public MyScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		if (isFirst) {
			viewGroup = (ViewGroup) getChildAt(0);
			viewOne = viewGroup.getChildAt(0);
			viewTwo = viewGroup.getChildAt(1);
			viewThree = viewGroup.getChildAt(2);
			hegihtDist = viewOne.getMeasuredHeight();
			scrollTo(0, 0);
			isFirst = false;
		}
		
		super.onLayout(changed, l, t, r, b);
		
		if (viewOne != null) {
			viewOne.layout(0, (int)(pullDownY + pullUpY), 
					viewOne.getMeasuredWidth(), (int)(pullDownY + pullUpY + viewOne.getMeasuredHeight()));
		}
		if (viewTwo != null) {
			viewTwo.layout(0, (int)(pullDownY + pullUpY + viewOne.getMeasuredHeight()),
					viewTwo.getMeasuredWidth(), (int)(pullDownY + pullUpY + viewOne.getMeasuredHeight() + viewTwo.getMeasuredHeight()));
		}
		if (viewThree != null) {
			viewThree.layout(0, 
					(int)(pullDownY + pullUpY + viewOne.getMeasuredHeight() 
							+ viewTwo.getMeasuredHeight()),
					viewThree.getMeasuredWidth(),
					(int)(pullDownY + pullUpY + viewOne.getMeasuredHeight() 
							+ viewTwo.getMeasuredHeight() + viewThree.getMeasuredHeight()));
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			lastY = ev.getY();
			y = 0;
			break;

		case MotionEvent.ACTION_MOVE:
			y = ev.getY() - lastY;
			if (canPullDown && y > 0 && y <= hegihtDist) {
				pullDownY = ev.getY() - lastY;
				requestLayout();
				return true;
			}else if (canPullUp && y < 0 && -y <= hegihtDist) {
				pullUpY = ev.getY() - lastY;
				pullDownY = 0;
				requestLayout();
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (canPullDown && y > 0) {
				if (pullDownY >= hegihtDist / 2) {
					pullDownY = hegihtDist;
					pullUpY = 0;
					canPullDown = false;
					canPullUp = true;
				}else {
					pullDownY = 0;
				}
				pullDownY = 0;
				requestLayout();
				return true;
			}else if (canPullUp && y < 0) {
				if (-pullUpY >= hegihtDist / 2) {
					pullUpY = -hegihtDist;
					pullDownY = 0;
					canPullDown = true;
					canPullUp = false;
				}else {
					pullUpY = 0;
				}
				requestLayout();
				return true;
			}
			y = 0;
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			super.onTouchEvent(ev);
			break;

		case MotionEvent.ACTION_MOVE:
			return false;

		case MotionEvent.ACTION_CANCEL:
			super.onTouchEvent(ev);
			break;

		case MotionEvent.ACTION_UP:
			return false;

		default:
			break;
		}

		return false;
	}
}