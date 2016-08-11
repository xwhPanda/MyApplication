package com.jiqu.helper.view;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.adapter.FragmentAdapter;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/5.
 */
public class FragmentAndViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private Context context;
    private ImageView imageView;
    private LinearLayout topTabLin;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private FragmentManager fragmentManager;
    private int current = 0;
    private int beforColor,afterColor,afterBackgroundColor,backgroundColor,tabStripColor;
    private boolean hasTabStrip = false;

    private String[] tabs;
    private List<Button> tabViewList = new ArrayList<>();

    public FragmentAndViewPager(Context context) {
        super(context);
        initView(context);
    }

    public FragmentAndViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.FragmentAndViewPager);
        beforColor = typedArray.getColor(R.styleable.FragmentAndViewPager_beforeColor,Tools.getColor(R.color.white));
        afterColor = typedArray.getColor(R.styleable.FragmentAndViewPager_afterColor,Tools.getColor(R.color.white));
        backgroundColor = typedArray.getColor(R.styleable.FragmentAndViewPager_backgroundColor,Tools.getColor(R.color.topBlue));
        afterBackgroundColor = typedArray.getColor(R.styleable.FragmentAndViewPager_afterBackgroundColor,Tools.getColor(R.color.topSelectedBlue));
        tabStripColor = typedArray.getColor(R.styleable.FragmentAndViewPager_tabStripColor,Tools.getColor(R.color.topBlue));
        hasTabStrip = typedArray.getBoolean(R.styleable.FragmentAndViewPager_hasTabStrip,false);

        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_fragment_layout,this);
        topTabLin = (LinearLayout) view.findViewById(R.id.topTabLin);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        imageView = (ImageView) view.findViewById(R.id.view);

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewHeight(topTabLin, MetricsTool.Rx * 74);
    }

    public void setTabLinHeight(int height){
        UIUtil.setViewHeight(topTabLin,MetricsTool.Rx * height);
    }

    public void setAfterBackgroundColor(int color){
        afterBackgroundColor = color;
    }

    public void setBackgroundColor(int color){
        backgroundColor = color;
    }

    public void setOffscreenPageLimit(int number){
        viewPager.setOffscreenPageLimit(number);
    }

    /** 设置fragment列表 **/
    public void setFragmentList(List<Fragment> fragmentList){
        this.fragmentList = fragmentList;
    }

    /** 设置fragmentManager **/
    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    /** 初始化数据 **/
    public void initData(){
        fragmentAdapter = new FragmentAdapter(fragmentManager,fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    public void setTab(String[] tabs){
        if (hasTabStrip){
            imageView.setBackgroundColor(tabStripColor);
            UIUtil.setViewWidth(imageView,MetricsTool.width / tabs.length);
        }else{
            imageView.setVisibility(GONE);
        }
        this.tabs = tabs;
        int length = tabs.length;
        for (int i = 0;i < length;i++){
            Button button = new Button(context);
            button.setGravity(Gravity.CENTER);
            button.setPadding(0,0,0,0);
            if (i == 0){
                button.setBackgroundColor(afterBackgroundColor);
            }else {
                button.setBackgroundColor(backgroundColor);
            }
            button.setText(tabs[i]);
            button.setOnClickListener(listener);
            button.setTextColor(beforColor);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT,1));
            UIUtil.setTextSize(button,35);
            topTabLin.addView(button);
            tabViewList.add(button);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0;i < tabViewList.size();i++){
            if (i == position){
                tabViewList.get(i).setBackgroundColor(afterBackgroundColor);
                tabViewList.get(i).setTextColor(afterColor);
            }else {
                tabViewList.get(i).setBackgroundColor(backgroundColor);
                tabViewList.get(i).setTextColor(beforColor);
            }
        }
        if (hasTabStrip){
            Animation animation = new TranslateAnimation(current * (MetricsTool.width / tabs.length),position * (MetricsTool.width / tabs.length),0,0);
            animation.setFillAfter(true);
            animation.setDuration(200);
            imageView.setAnimation(animation);
            animation.startNow();
        }
        current = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0 ; i < tabViewList.size() ; i++){
                if (view == tabViewList.get(i)){
                    viewPager.setCurrentItem(i);
                }
            }
        }
    };
}