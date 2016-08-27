package com.jiqu.helper.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.RecommendListData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.RefreshView;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 * 首页榜单
 */
public class HomeListFragment extends BaseFragment implements
        AnimRFRecyclerView.LoadDataListener,View.OnClickListener,RecycleViewOnItemClickListener {
    private final String OKHTTP_TAG_APP = "HomeListFragmentApp";
    private final String OKHTTP_TAG_GAME = "HomeListFragmentGame";
    private View spaceView;
    private LinearLayout titleLin;
    private LinearLayout appRankLin;
    private LinearLayout gameRankLin;
    private ImageView appImage;
    private TextView appName;
    private ImageView gameImage;
    private TextView gameName;
    private AnimRFRecyclerView recyclerView,gameRecyclerView;
    private RelativeLayout appRel,gameRel;
    private RefreshView appRefreshView,gameRefreshView;
    private List<GameInfo> infoList = new ArrayList<>();
    private List<GameInfo> gameInfoList = new ArrayList<>();
    private RecommendAppAdapter appAdapter,gameAdapter;
    private boolean isGameFirst = true;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_list_layout,null);
        return view;
    }

    @Override
    public void initView() {
        appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,infoList);
        gameAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,gameInfoList);
        appAdapter.setListener(this);
        gameAdapter.setListener(this);
        spaceView = (View) view.findViewById(R.id.spaceView);
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        appRankLin = (LinearLayout) view.findViewById(R.id.appRankLin);
        gameRankLin = (LinearLayout) view.findViewById(R.id.gameRankLin);
        appRel = (RelativeLayout) view.findViewById(R.id.appRel);
        gameRel = (RelativeLayout) view.findViewById(R.id.gameRel);
        appImage = (ImageView) view.findViewById(R.id.appImage);
        gameImage = (ImageView) view.findViewById(R.id.gameImage);
        appName = (TextView) view.findViewById(R.id.appName);
        gameName = (TextView) view.findViewById(R.id.gameName);
        recyclerView = (AnimRFRecyclerView) view.findViewById(R.id.recycleView);
        gameRecyclerView = (AnimRFRecyclerView) view.findViewById(R.id.gameRecycleView);
        appRefreshView = (RefreshView) view.findViewById(R.id.appRefreshView);
        gameRefreshView = (RefreshView) view.findViewById(R.id.gameRefreshView);

        appRankLin.setOnClickListener(this);
        gameRankLin.setOnClickListener(this);

        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLoadDataListener(this);
        recyclerView.setRefreshEnable(false);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        recyclerView.setAdapter(appAdapter);

        AnimRFLinearLayoutManager linearLayoutManager = new AnimRFLinearLayoutManager(mActivity);
        gameRecyclerView.setLayoutManager(linearLayoutManager);
        gameRecyclerView.setHasFixedSize(true);
        gameRecyclerView.setLoadDataListener(this);
        gameRecyclerView.setRefreshEnable(false);
        gameRecyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        gameRecyclerView.setAdapter(gameAdapter);
    }

    @Override
    public void initViewSize() {
        try {
            UIUtil.setViewSize(appImage,Rx * 72,Rx * 72);
            UIUtil.setViewSize(gameImage,Rx * 72,Rx * 72);
            UIUtil.setViewHeight(titleLin,Rx * 135);
            UIUtil.setViewHeight(spaceView,Rx * 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        loadAppData();
    }


    private void loadAppData(){
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_APP_RANK,OKHTTP_TAG_APP,null,null).build(), RecommendListData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                RecommendListData recommendListData = (RecommendListData)data;
                if (recommendListData != null && recommendListData.getStatus() == 1){
                    appRefreshView.setVisibility(View.GONE);
                    infoList.clear();
                    infoList.addAll(recommendListData.getData());
                    recyclerView.bringToFront();
                }
            }
        });
    }

    private void loadGameData(){
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_GAME_RANK,OKHTTP_TAG_GAME,null,null).build(), RecommendListData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                RecommendListData recommendListData = (RecommendListData)data;
                if (recommendListData != null && recommendListData.getStatus() == 1){
                    gameRefreshView.setVisibility(View.GONE);
                    gameInfoList.clear();
                    gameInfoList.addAll(recommendListData.getData());
                    gameRecyclerView.bringToFront();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_APP);
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_GAME);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    private void changeState(View view){
       if (view.getId() == R.id.appRankLin){
           appImage.setBackgroundResource(R.drawable.app_list_selected);
           gameImage.setBackgroundResource(R.drawable.game_list_default);
           appName.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
           gameName.setTextColor(Tools.getColor(R.color.list_rank_default_color));
       }else if(view.getId() == R.id.gameRankLin){
           appImage.setBackgroundResource(R.drawable.app_list_default);
           gameImage.setBackgroundResource(R.drawable.game_list_selected);
           appName.setTextColor(Tools.getColor(R.color.list_rank_default_color));
           gameName.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
       }
    }

    @Override
    public void onClick(View view) {
        changeState(view);
        if (view.getId() == R.id.appRankLin){
            gameRel.setVisibility(View.GONE);
            appRel.setVisibility(View.VISIBLE);
        }else if (view.getId() == R.id.gameRankLin){
            if (isGameFirst){
                isGameFirst = false;
                loadGameData();
            }
            appRel.setVisibility(View.GONE);
            gameRel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View view, BaseAdapter adapter, int position) {
        Intent intent = new Intent(mActivity,DetailActivity.class);
        if (adapter == appAdapter){
            if (Tools.getType(infoList.get(position).getType()) == -1){
                return;
            }else {
                intent.putExtra("type",Tools.getType(infoList.get(position).getType()));
            }
            intent.putExtra("id",infoList.get(position).getId());
            intent.putExtra("name",infoList.get(position).getApply_name());
        }else if (adapter == gameAdapter){
            if (Tools.getType(gameInfoList.get(position).getType()) == -1){
                return;
            }else {
                intent.putExtra("type",Tools.getType(gameInfoList.get(position).getType()));
            }
            intent.putExtra("id",gameInfoList.get(position).getId());
            intent.putExtra("name",gameInfoList.get(position).getApply_name());
        }
        startActivity(intent);
    }
}
