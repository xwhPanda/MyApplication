package com.jiqu.helper.fragments;

import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendClassificationAdapter;
import com.jiqu.helper.data.RecommendClassificationData;
import com.jiqu.helper.data.RecommendClassificationInfo;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.interfaces.GetDataCallback;
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
 * 首页分类
 */
public class HomeClassificationFragment extends BaseFragment implements AnimRFRecyclerView.LoadDataListener{
    private final String OKHTTP_TAG = "HomeClassificationFragment";
    private AnimRFRecyclerView classificationRecycleView;
    private List<RecommendClassificationItemData> itemDatas = new ArrayList<>();
    private RecommendClassificationAdapter adapter;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_classification_layout,null);
        return view;
    }

    @Override
    public void initView() {
        adapter = new RecommendClassificationAdapter(mActivity,R.layout.recommend_classification_item_layout,itemDatas);
        classificationRecycleView = (AnimRFRecyclerView) view.findViewById(R.id.classificationRecycleView);

        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        classificationRecycleView.setLayoutManager(manager);
        classificationRecycleView.setHasFixedSize(true);
        classificationRecycleView.setLoadDataListener(this);
        classificationRecycleView.addFootView(new TextView(mActivity));
        classificationRecycleView.setAdapter(adapter);

    }

    @Override
    public void initViewSize() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_CLASSIFICATION, OKHTTP_TAG, null, null).build(),
                RecommendClassificationData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                RecommendClassificationData recommendClassificationData = (RecommendClassificationData)data;
                if (recommendClassificationData.getStatus() == 1){
                    itemDatas.clear();
                    itemDatas.addAll(recommendClassificationData.getData());
                    classificationRecycleView.bringToFront();
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkHttpManager.getInstance().cancleByTag(OKHTTP_TAG);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
