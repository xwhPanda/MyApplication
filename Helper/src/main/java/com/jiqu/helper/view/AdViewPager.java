package com.jiqu.helper.view;

import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.ViewPagerAdapter;
import com.jiqu.helper.data.RecommendChoiceAdInfo;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class AdViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private RelativeLayout radioRel;
    private LinearLayout radioGroup;
    private TextView name;
    private ImageView[] radios;
    private List<ImageView> contentViews = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    private ViewPagerAdapter adapter;
    private Context context;
    private List<RecommendChoiceAdInfo> adInfos;
    private PagerTimer timer;
    private boolean isRunning = false;
    private int currentIndex = 0;

    public AdViewPager(Context context) {
        super(context);
        initView(context);
    }

    public AdViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        timer = new PagerTimer(Integer.MAX_VALUE,5000);
        adapter = new ViewPagerAdapter(contentViews,urlList);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_viewpager_layout,this);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        radioRel = (RelativeLayout) view.findViewById(R.id.radioRel);
        radioGroup = (LinearLayout) view.findViewById(R.id.radioGroup);
        name = (TextView) view.findViewById(R.id.name);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        initViewSize();
    }

    private void initViewSize(){
        try {
            UIUtil.setViewHeight(radioRel, MetricsTool.Rx * 66);

            UIUtil.setTextSize(name,35);
            UIUtil.setViewSizeMargin(name,0,0,MetricsTool.Rx * 54,0);
            UIUtil.setViewSizeMargin(radioGroup,MetricsTool.Rx * 45,0,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTimer(){
        if (isRunning)
            return;
        isRunning = true;
        timer.start();
    }

    public void cancelTimer(){
        if (!isRunning)
            return;
        isRunning = false;
        timer.cancel();
    }

    public void setData(List<RecommendChoiceAdInfo> adInfos){
        this.adInfos = adInfos;
        refreshView();
    }

    public void refreshView(){
        if (adInfos != null && adInfos.size() > 0){
            radios = new ImageView[adInfos.size()];
            for (int i = 0;i < adInfos.size();i++){
                final ImageView imageView = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 23),(int)(MetricsTool.Rx * 23));
                if (i != 0){
                    params.leftMargin = (int)(MetricsTool.Rx * 10);
                    imageView.setBackgroundResource(R.mipmap.point_default);
                }else {
                    imageView.setBackgroundResource(R.mipmap.point_selected);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            name.setText(adInfos.get(0).getRotate_title());
                        }
                    });
                }
                imageView.setLayoutParams(params);
                radios[i] = imageView;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        radioGroup.addView(imageView);
                    }
                });

                SimpleDraweeView contentView = new SimpleDraweeView(context);
                RelativeLayout.LayoutParams contentParam = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                contentView.setLayoutParams(contentParam);
                urlList.add(adInfos.get(i).getRotate_pic());
                contentViews.add(contentView);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0;i < radios.length;i++){
            if (i == position)
                radios[i].setBackgroundResource(R.mipmap.point_selected);
            else
                radios[i].setBackgroundResource(R.mipmap.point_default);
        }
        name.setText(adInfos.get(position).getRotate_title());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class PagerTimer extends CountDownTimer{

        public PagerTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            currentIndex++;
            viewPager.setCurrentItem(currentIndex % adInfos.size());
        }

        @Override
        public void onFinish() {

        }
    }
}
