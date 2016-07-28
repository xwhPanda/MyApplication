package com.jiqu.helper.adapter;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
	private List<ImageView> contentImgs;
	private List<String> urlList;

	public ViewPagerAdapter(List<ImageView> contentImgs,List<String> urlList) {
		// TODO Auto-generated constructor stub
		this.contentImgs = contentImgs;
		this.urlList = urlList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contentImgs.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(contentImgs.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(contentImgs.get(position));
		contentImgs.get(position).setImageURI(Uri.parse(urlList.get(position)));
		return contentImgs.get(position);
	}
}
