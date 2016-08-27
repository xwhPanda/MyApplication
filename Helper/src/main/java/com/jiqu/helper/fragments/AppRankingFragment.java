package com.jiqu.helper.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.AppRankingData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/11.
 */
public class AppRankingFragment extends BaseFragment implements RecycleViewOnItemClickListener{
    private final String OKHTTP_TAG = "AppRankingFragment";
    private MyRecycleView myRecycleView;
    private RecommendAppAdapter appAdapter;
    private List<GameInfo> gameInfoList = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.app_ranking_layout,null);
        return view;
    }

    @Override
    public void initView() {
        appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,gameInfoList);
        appAdapter.setListener(this);
        myRecycleView = (MyRecycleView)view.findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        myRecycleView.setLayoutManager(manager);
        myRecycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        myRecycleView.setHasFixedSize(true);
        myRecycleView.setAdapter(appAdapter);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.APP_RANKING, OKHTTP_TAG, null, null).build(), AppRankingData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                AppRankingData rankingData = (AppRankingData) data;
                if (rankingData != null){
                    if (rankingData.getStatus() == 1){
                        gameInfoList.addAll(rankingData.getData());
                        appAdapter.notifyDataSetChanged();
                    }else if (rankingData.getStatus() == 0){

                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
        gameInfoList.clear();
        appAdapter = null;
    }

    @Override
    public void onItemClick(View view, BaseAdapter adapter, int position) {
        Intent intent = new Intent(mActivity, DetailActivity.class);
        if (Tools.getType(gameInfoList.get(position).getType()) == -1) {
            return;
        } else {
            intent.putExtra("type", Tools.getType(gameInfoList.get(position).getType()));
        }
        intent.putExtra("id", gameInfoList.get(position).getId());
        intent.putExtra("name", gameInfoList.get(position).getApply_name());
        startActivity(intent);
    }
}
