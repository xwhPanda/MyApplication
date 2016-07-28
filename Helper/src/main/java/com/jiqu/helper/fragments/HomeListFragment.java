package com.jiqu.helper.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

/**
 * Created by xiongweihua on 2016/7/6.
 * 首页榜单
 */
public class HomeListFragment extends BaseFragment implements
        AnimRFRecyclerView.LoadDataListener,View.OnClickListener{
    private View spaceView;
    private LinearLayout titleLin;
    private LinearLayout appRankLin;
    private LinearLayout gameRankLin;
    private ImageView appImage;
    private TextView appName;
    private ImageView gameImage;
    private TextView gameName;
    private AnimRFRecyclerView recyclerView;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_list_layout,null);
        return view;
    }

    @Override
    public void initView() {
        spaceView = (View) view.findViewById(R.id.spaceView);
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        appRankLin = (LinearLayout) view.findViewById(R.id.appRankLin);
        gameRankLin = (LinearLayout) view.findViewById(R.id.gameRankLin);
        appImage = (ImageView) view.findViewById(R.id.appImage);
        gameImage = (ImageView) view.findViewById(R.id.gameImage);
        appName = (TextView) view.findViewById(R.id.appName);
        gameName = (TextView) view.findViewById(R.id.gameName);
        recyclerView = (AnimRFRecyclerView) view.findViewById(R.id.recycleView);

        appRankLin.setOnClickListener(this);
        gameRankLin.setOnClickListener(this);

        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLoadDataListener(this);
        recyclerView.setRefreshEnable(false);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        recyclerView.setAdapter(new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,null));
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        }else if (view.getId() == R.id.gameRankLin){

        }
    }
}
