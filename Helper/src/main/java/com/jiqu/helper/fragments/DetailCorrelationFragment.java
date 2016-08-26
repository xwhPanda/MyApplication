package com.jiqu.helper.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.DetailCorrelationData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/19.
 */
public class DetailCorrelationFragment extends BaseFragment{
    private final String OKHTTP_TAG = "DetailCorrelationFragment";
    private String id;
    private MyRecycleView recyclerView;
    private RecommendAppAdapter appAdapter;
    private List<GameInfo> gameInfoList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.detail_correlation_layout,null);
        return view;
    }

    @Override
    public void initView() {
        id = getArguments().getString("id");
        appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,gameInfoList);
        recyclerView = (MyRecycleView)view.findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        recyclerView.setAdapter(appAdapter);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.APP_DETAIL_CORRELATION + id, OKHTTP_TAG, null, null).build(),
                DetailCorrelationData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                DetailCorrelationData correlationData = (DetailCorrelationData) data;
                if (correlationData != null){
                    if (correlationData.getStatus() == 1 && correlationData.getData() != null){
                        gameInfoList.clear();
                        gameInfoList.addAll(correlationData.getData());
                        appAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
