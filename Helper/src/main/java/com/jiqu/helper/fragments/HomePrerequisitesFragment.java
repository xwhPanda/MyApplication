package com.jiqu.helper.fragments;

import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.FindingsAdapter;
import com.jiqu.helper.adapter.RecommendPrerequisitesAdapter;
import com.jiqu.helper.data.RecommendPrerequisitesData;
import com.jiqu.helper.interfaces.GetDataCallback;
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
 * 首页必备
 */
public class HomePrerequisitesFragment extends BaseFragment implements View.OnClickListener{
    private final String OKHTTP_TAG_APP = "HomePrerequisitesFragmentApp";
    private final String OKHTTP_TAG_GAME = "HomePrerequisitesFragmentGame";
    private LinearLayout titleLin;
    private LinearLayout appLin;
    private LinearLayout gameLin;
    private ImageView appImage;
    private ImageView gameImage;
    private TextView appTitle;
    private TextView gameTitle;
    private RelativeLayout appPrerequisitesRel,gamePrerequisitesRel;
    private AnimRFRecyclerView appPrerequisitesRecycleView,gamePrerequisitesRecycleView;
    private RefreshView appRefreshView,gameRefreshView;
    private RecommendPrerequisitesAdapter appAdapter,gameAdapter;
    private List<RecommendPrerequisitesData.RecommendPrerequisitesInfo> appList = new ArrayList<>();
    private List<RecommendPrerequisitesData.RecommendPrerequisitesInfo> gameList = new ArrayList<>();
    private boolean isFirst = true;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_prerequisites_layout,null);
        return view;
    }

    @Override
    public void initView() {
        appAdapter = new RecommendPrerequisitesAdapter(mActivity,R.layout.recommend_prerequisites_layout,appList);
        gameAdapter = new RecommendPrerequisitesAdapter(mActivity,R.layout.recommend_prerequisites_layout,gameList);

        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        appLin = (LinearLayout) view.findViewById(R.id.appLin);
        gameLin = (LinearLayout) view.findViewById(R.id.gameLin);
        appImage = (ImageView) view.findViewById(R.id.appImage);
        gameImage = (ImageView) view.findViewById(R.id.gameImage);
        appTitle = (TextView) view.findViewById(R.id.appTitle);
        gameTitle = (TextView) view.findViewById(R.id.gameTitle);
        appPrerequisitesRel = (RelativeLayout) view.findViewById(R.id.appPrerequisitesRel);
        gamePrerequisitesRel = (RelativeLayout) view.findViewById(R.id.gamePrerequisitesRel);
        appRefreshView = (RefreshView) view.findViewById(R.id.appRefreshView);
        gameRefreshView = (RefreshView) view.findViewById(R.id.gameRefreshView);

        appLin.setOnClickListener(this);
        gameLin.setOnClickListener(this);

        appPrerequisitesRecycleView = (AnimRFRecyclerView)view.findViewById(R.id.appPrerequisitesRecycleView);
        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        appPrerequisitesRecycleView.setLayoutManager(manager);
        appPrerequisitesRecycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        appPrerequisitesRecycleView.setHasFixedSize(true);
        appPrerequisitesRecycleView.setRefreshEnable(false);
        appPrerequisitesRecycleView.setAdapter(appAdapter);

        gamePrerequisitesRecycleView = (AnimRFRecyclerView) view.findViewById(R.id.gamePrerequisitesRecycleView);
        AnimRFLinearLayoutManager layoutManager = new AnimRFLinearLayoutManager(mActivity);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        gamePrerequisitesRecycleView.setLayoutManager(layoutManager);
        gamePrerequisitesRecycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        gamePrerequisitesRecycleView.setHasFixedSize(true);
        gamePrerequisitesRecycleView.setRefreshEnable(false);
        gamePrerequisitesRecycleView.setAdapter(gameAdapter);
    }

    private void loadAppData(){
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RCOMMEND_APP_PREREQUISITES,OKHTTP_TAG_APP,null,null).build(), RecommendPrerequisitesData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                appRefreshView.setVisibility(View.GONE);
                RecommendPrerequisitesData prerequisitesData = (RecommendPrerequisitesData)data;
                if(prerequisitesData != null && prerequisitesData.getStatus() == 1){
                    appList.clear();
                    appList.addAll(prerequisitesData.getData());
                    appPrerequisitesRecycleView.bringToFront();
                }
            }
        });
    }

    private void loadGameData(){
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.RECOMMEND_GAME_PREREQUISITES,OKHTTP_TAG_GAME,null,null).build(), RecommendPrerequisitesData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                gameRefreshView.setVisibility(View.GONE);
                RecommendPrerequisitesData prerequisitesData = (RecommendPrerequisitesData)data;
                if(prerequisitesData != null && prerequisitesData.getStatus() == 1){
                    gameList.clear();
                    gameList.addAll(prerequisitesData.getData());
                    gamePrerequisitesRecycleView.bringToFront();
                }
            }
        });
    }

    private void changeState(View view){
        if (view.getId() == R.id.appLin){
            gamePrerequisitesRel.setVisibility(View.GONE);
            appPrerequisitesRel.setVisibility(View.VISIBLE);
            appImage.setBackgroundResource(R.drawable.app_list_selected);
            gameImage.setBackgroundResource(R.drawable.game_list_default);
            appTitle.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
            gameTitle.setTextColor(Tools.getColor(R.color.list_rank_default_color));
        }else if(view.getId() == R.id.gameLin){
            appPrerequisitesRel.setVisibility(View.GONE);
            gamePrerequisitesRel.setVisibility(View.VISIBLE);
            appImage.setBackgroundResource(R.drawable.app_list_default);
            gameImage.setBackgroundResource(R.drawable.game_list_selected);
            appTitle.setTextColor(Tools.getColor(R.color.list_rank_default_color));
            gameTitle.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
            if (isFirst){
                loadGameData();
                isFirst = false;
            }
        }
    }

    @Override
    public void initViewSize() {
        try {
            UIUtil.setViewSize(appImage,Rx * 72,Rx * 72);
            UIUtil.setViewSize(gameImage,Rx * 72,Rx * 72);
            UIUtil.setViewHeight(titleLin,Rx * 135);
            UIUtil.setViewSizeMargin(titleLin,0,Rx * 20,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        loadAppData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        changeState(view);
    }
}
