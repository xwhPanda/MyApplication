package com.jiqu.helper.fragments;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.adapter.RecommendRecycleAdapter;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.RecommendData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.AdViewPager;
import com.jiqu.helper.view.LoadMoreView;
import com.jiqu.helper.view.RefreshView;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 * 首页精选
 */
public class HomeChoiceFragment extends BaseFragment implements AnimRFRecyclerView.LoadDataListener
            ,RecycleViewOnItemClickListener {
    private final String OKHTTP_TAG = "HomeChoiceFragment";
    private View headView;
    private AdViewPager adViewPager;
    private TextView recommendTip;
    private RecyclerView recommendRecycleView;
    private RefreshView refreshView;
    private AnimRFRecyclerView recommendAppRecycleView;
    private Button oneKeyDownload;
    private RecommendAppAdapter recommendAppAdapter;
    /** 一键装机adapter **/
    private RecommendRecycleAdapter recommendRecycleAdapter;
    private List<GameInfo> recommendAppList = new ArrayList<>();
    private List<GameInfo> oneKeyInstallList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_choice_layout,null);
        return view;
    }

    @Override
    public void initView() {
        recommendAppAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,recommendAppList);
        recommendAppAdapter.setListener(this);
        recommendRecycleAdapter = new RecommendRecycleAdapter(mActivity,R.layout.recommend_recycle_item,oneKeyInstallList);
        recommendRecycleAdapter.setListener(this);

        headView = LayoutInflater.from(mActivity).inflate(R.layout.home_choice_head_layout,null);
        adViewPager = (AdViewPager) headView.findViewById(R.id.adViewPager);
        recommendTip = (TextView) headView.findViewById(R.id.recommendTip);
        recommendRecycleView = (RecyclerView) headView.findViewById(R.id.recommendRecycleView);
        oneKeyDownload = (Button) headView.findViewById(R.id.oneKeyDownload);
        recommendAppRecycleView = (AnimRFRecyclerView) view.findViewById(R.id.recommendAppRecycleView);
        refreshView = (RefreshView) view.findViewById(R.id.refreshView);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        recommendRecycleView.setLayoutManager(manager);
        recommendRecycleView.setHasFixedSize(true);
        recommendRecycleView.addItemDecoration(new SpaceItemDecoration(0,Rx * 15));
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        recommendRecycleView.setAdapter(recommendRecycleAdapter);

        AnimRFLinearLayoutManager layoutManager = new AnimRFLinearLayoutManager(mActivity);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recommendAppRecycleView.setLayoutManager(layoutManager);
        recommendAppRecycleView.setRefreshEnable(false);
        recommendAppRecycleView.setHasFixedSize(true);
        recommendAppRecycleView.addItemDecoration(new SpaceItemDecoration(Rx * 2,0));
        recommendAppRecycleView.setAdapter(recommendAppAdapter);
        recommendAppRecycleView.addHeaderView(headView);
        recommendAppRecycleView.addFootView(new LoadMoreView(mActivity));
        recommendAppRecycleView.setLoadDataListener(this);
    }

    @Override
    public void initViewSize() {
        try {
            UIUtil.setTextSize(recommendTip,35);
            UIUtil.setViewSize(oneKeyDownload,Rx * 194,Rx * 100);
            UIUtil.setViewHeight(adViewPager,Rx * 460);
            UIUtil.setViewSizeMargin(recommendTip,Rx * 35,Rx * 35,0,0);
            UIUtil.setViewSizeMargin(recommendRecycleView,0,Rx * 40,Rx * 15,Rx * 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_APP,OKHTTP_TAG,null,null).build(), RecommendData.class,new GetDataCallback(){

            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                refreshView.setVisibility(View.GONE);
                final RecommendData recommendData = (RecommendData) data;
                adViewPager.setData(recommendData.getData1());
                adViewPager.startTimer();
                setOneKeyInstallList(recommendData.getData3());
                setRecommendAppData(recommendData.getData4());
                recommendRecycleView.bringToFront();
            }
        });
    }

    /**设置一键装机数据**/
    private void setOneKeyInstallList(List<GameInfo> gameInfoList){
        oneKeyInstallList.clear();
        oneKeyInstallList.addAll(gameInfoList);
    }

    /**设置推荐数据**/
    private void setRecommendAppData(List<GameInfo> gameInfoList){
        recommendAppList.clear();
        recommendAppList.addAll(gameInfoList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adViewPager.cancelTimer();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(View view, BaseAdapter baseAdapter,int position) {
        if (baseAdapter instanceof RecommendAppAdapter){
            Intent intent = new Intent(mActivity,DetailActivity.class);
            if (Tools.getType(recommendAppList.get(position).getType()) == -1){
                return;
            }else {
                switch (Tools.getType(recommendAppList.get(position).getType())){
                    case 0:
                        intent.putExtra("type",0);
                        break;
                    case 1:
                        intent.putExtra("type",1);
                        break;
                }
            }
            intent.putExtra("id",recommendAppList.get(position).getId());
            intent.putExtra("name",recommendAppList.get(position).getApply_name());
            startActivity(intent);
        }else if(baseAdapter instanceof RecommendRecycleAdapter){

        }
    }
}
