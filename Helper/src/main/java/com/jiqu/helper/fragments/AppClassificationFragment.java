package com.jiqu.helper.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendClassificationAdapter;
import com.jiqu.helper.data.RecommendClassificationData;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.MyRecycleView;
import com.jiqu.helper.view.RefreshView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/11.
 */
public class AppClassificationFragment extends BaseFragment implements MyRecycleView.OnLoadDataListener{
    private final String OKHTTP_TAG = "AppClassificationFragment";
    private MyRecycleView classificationRecycleView;
    private RefreshView refreshView;
    private List<String> titleList = new ArrayList<>();
    private List<List<RecommendClassificationItemData>> classificationList = new ArrayList<>();
    private RecommendClassificationAdapter adapter;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.app_classification_layout,null);
        return view;
    }

    @Override
    public void initView() {
        adapter = new RecommendClassificationAdapter(mActivity,R.layout.recommend_classification_item_layout,classificationList,titleList);
        classificationRecycleView = (MyRecycleView) view.findViewById(R.id.classificationRecycleView);
        refreshView = (RefreshView) view.findViewById(R.id.refreshView);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        classificationRecycleView.setOnLoadDataListener(this);
        classificationRecycleView.setLayoutManager(manager);
        classificationRecycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        classificationRecycleView.setAdapter(adapter);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.APP_CLASSIFICATION, OKHTTP_TAG, null, null).build(), RecommendClassificationData.class, new GetDataCallback() {
                    @Override
                    public void onFailed(Call call, IOException e) {
                        classificationRecycleView.cancelLoad();
                        Tools.showToast(mActivity,R.string.load_failed);
                    }

                    @Override
                    public void onSucceed(Call call, Object data) {
                        refreshView.setVisibility(View.GONE);
                        classificationRecycleView.loadComplete();
                        RecommendClassificationData classificationData = (RecommendClassificationData)data;
                        if (classificationData != null && classificationData.getStatus() == 1){
                            titleList.clear();
                            titleList.addAll(classificationData.getData1());
                            classificationList.clear();
                            classificationList.addAll(classificationData.getData2());
                        }else if(classificationData.getStatus() == 0){
                            Tools.showToast(mActivity,R.string.load_no_more);
                        }
                    }
                }
        );
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        classificationRecycleView.cancelLoad();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
    }
}
