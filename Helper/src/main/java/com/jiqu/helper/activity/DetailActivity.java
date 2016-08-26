package com.jiqu.helper.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.BaseFragmentActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.data.DetailData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.fragments.DetailCommentFragment;
import com.jiqu.helper.fragments.DetailCorrelationFragment;
import com.jiqu.helper.fragments.DetailSummaryFragment;
import com.jiqu.helper.fragments.GameChoiceFragment;
import com.jiqu.helper.fragments.GameRankingFragment;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.FragmentAndViewPager;
import com.jiqu.helper.view.RatingBar;
import com.jiqu.helper.view.TitleBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/29.
 */
public class DetailActivity extends BaseFragmentActivity{
    /** 类型0：应用，1：游戏 **/
    private int activityType = 0;
    /** 应用或游戏名 **/
    private String nameStr;
    /** 应用或游戏ID **/
    private String id;
    private String loadUrl;
    private final String OKHTTP_TAG = "DetailActivity";
    private TitleBar titleBar;
    private SimpleDraweeView icon;
    private TextView name;
    private TextView type;
    private TextView size;
    private RatingBar ratingBar;
    private CheckBox free,safe,noAd;
    private Button button;
    private TextView download;
    private FragmentAndViewPager viewPager;
    private DetailSummaryFragment detailSummaryFragment;
    private DetailCommentFragment detailCommentFragment;
    private DetailCorrelationFragment detailCorrelationFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.detail_layout;
    }

    @Override
    public void initView() {
        activityType = getIntent().getIntExtra("type",0);
        nameStr = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        if (activityType == 0){
            loadUrl = RequestTools.GAME_DETAIL + id;
        } else if (activityType == 1) {
            loadUrl = RequestTools.APP_DETAIL + id;
        }

        titleBar = (TitleBar) findViewById(R.id.titleBar);
        icon = (SimpleDraweeView) findViewById(R.id.icon);
        name = (TextView) findViewById(R.id.name);
        type = (TextView) findViewById(R.id.type);
        size = (TextView) findViewById(R.id.size);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        free = (CheckBox) findViewById(R.id.free);
        safe = (CheckBox) findViewById(R.id.safe);
        noAd = (CheckBox) findViewById(R.id.noAd);
        button = (Button) findViewById(R.id.button);
        download = (TextView) findViewById(R.id.download);
        viewPager = (FragmentAndViewPager) findViewById(R.id.fragmentAndViewPager);

        titleBar.setTitle(nameStr);
        titleBar.setActivity(this);
        ratingBar.setRating(3);

        detailSummaryFragment = new DetailSummaryFragment();
        detailCommentFragment = new DetailCommentFragment();
        Bundle commentBundle = new Bundle();
        commentBundle.putString("id",id);
        detailCommentFragment.setArguments(commentBundle);

        detailCorrelationFragment = new DetailCorrelationFragment();
        Bundle correlationBundle = new Bundle();
        commentBundle.putString("id",id);
        detailCorrelationFragment.setArguments(correlationBundle);
        fragmentList.add(detailSummaryFragment);
        fragmentList.add(detailCommentFragment);
        fragmentList.add(detailCorrelationFragment);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setFragmentList(fragmentList);
        viewPager.setTabLinHeight(135);
        viewPager.setFragmentManager(getSupportFragmentManager());
        String[] tabs = new String[]{"简介","评论","相关"};
        viewPager.setTab(tabs);
        viewPager.initData();
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewSize(icon, Rx * 145,Rx * 145);
        UIUtil.setViewHeight(ratingBar,Rx * 30);
        UIUtil.setViewSize(button,Rx * 100,Rx * 100);
        UIUtil.setTextSize(name,45);
        UIUtil.setTextSize(type,35);
        UIUtil.setTextSize(size,35);
        UIUtil.setTextSize(free,20);
        UIUtil.setTextSize(safe,20);
        UIUtil.setTextSize(noAd,20);

        UIUtil.setViewHeight(viewPager,MetricsTool.height - Rx * 170 - Rx  * 120 - Rx  * 135);

        try {
            UIUtil.setViewSizeMargin(icon,Rx * 60,0,Rx * 30,0);
            UIUtil.setViewSizeMargin(button,0,0,Rx * 45,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(loadUrl, OKHTTP_TAG, null, null).build(), DetailData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                DetailData detailData = (DetailData) data;
                if (detailData != null && detailData.getStatus() == 1){
                    GameInfo gameInfo = detailData.getData();
                    icon.setImageURI(Uri.parse(gameInfo.getIcon()));
                    name.setText(gameInfo.getApply_name());
                    type.setText(gameInfo.getColumn());
                    size.setText(String.valueOf(gameInfo.getSize()));
                    ratingBar.setRating(gameInfo.getStar());
                    if (gameInfo.getPic() != null && gameInfo.getPic().size() > 0){
                        detailSummaryFragment.setData(gameInfo.getPic());
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
    }
}
