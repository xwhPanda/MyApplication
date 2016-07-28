package com.jiqu.helper.fragments;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.FindingsAdapter;
import com.jiqu.helper.data.RecommendFindingsData;
import com.jiqu.helper.data.RecommendFindingsItemInfo;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 * 首页发现
 */
public class HomeFindingsFragment extends BaseFragment {
    private final String OKHTTP_TAG = "HomeFindingsFragment";
    private AnimRFRecyclerView findingsRecycleView;
    private FindingsAdapter adapter;
    private List<RecommendFindingsItemInfo> infoList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_findings_layout, null);
        return view;
    }

    @Override
    public void initView() {
        adapter = new FindingsAdapter(mActivity, R.layout.findings_item_layout, infoList);
        findingsRecycleView = (AnimRFRecyclerView) view.findViewById(R.id.findingsRecycleView);

        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        findingsRecycleView.setLayoutManager(manager);
        findingsRecycleView.setHasFixedSize(true);
        findingsRecycleView.setRefreshEnable(false);
        findingsRecycleView.addItemDecoration(new SpaceItemDecoration(2, 0));
        findingsRecycleView.setAdapter(adapter);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_FINDINGS, OKHTTP_TAG, null, null).build(),
                RecommendFindingsData.class, new GetDataCallback() {
                    @Override
                    public void onFailed(Call call, IOException e) {

                    }

                    @Override
                    public void onSucceed(Call call, Object data) {
                        final RecommendFindingsData recommendFindingsData = (RecommendFindingsData) data;
                        if (recommendFindingsData.getStatus() == 1) {
                            infoList.clear();
                            infoList.addAll(recommendFindingsData.getData());
                            adapter.notifyDataSetChanged();
                            findingsRecycleView.bringToFront();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkHttpManager.getInstance().cancleByTag(OKHTTP_TAG);
    }
}
