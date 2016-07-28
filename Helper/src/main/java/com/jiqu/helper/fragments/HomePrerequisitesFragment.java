package com.jiqu.helper.fragments;

import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.FindingsAdapter;
import com.jiqu.helper.adapter.RecommendPrerequisitesAdapter;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

/**
 * Created by xiongweihua on 2016/7/6.
 * 首页必备
 */
public class HomePrerequisitesFragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout titleLin;
    private LinearLayout appLin;
    private LinearLayout gameLin;
    private ImageView appImage;
    private ImageView gameImage;
    private TextView appTitle;
    private TextView gameTitle;
    private AnimRFRecyclerView prerequisitesRecycleView;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_prerequisites_layout,null);
        return view;
    }

    @Override
    public void initView() {
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        appLin = (LinearLayout) view.findViewById(R.id.appLin);
        gameLin = (LinearLayout) view.findViewById(R.id.gameLin);
        appImage = (ImageView) view.findViewById(R.id.appImage);
        gameImage = (ImageView) view.findViewById(R.id.gameImage);
        appTitle = (TextView) view.findViewById(R.id.appTitle);
        gameTitle = (TextView) view.findViewById(R.id.gameTitle);

        appLin.setOnClickListener(this);
        gameLin.setOnClickListener(this);

        prerequisitesRecycleView = (AnimRFRecyclerView)view.findViewById(R.id.prerequisitesRecycleView);
        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        prerequisitesRecycleView.setLayoutManager(manager);
        prerequisitesRecycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        prerequisitesRecycleView.setHasFixedSize(true);
        prerequisitesRecycleView.setRefreshEnable(false);
        prerequisitesRecycleView.setAdapter(new RecommendPrerequisitesAdapter(mActivity,R.layout.recommend_prerequisites_layout,null));
    }

    private void changeState(View view){
        if (view.getId() == R.id.appLin){
            appImage.setBackgroundResource(R.drawable.app_list_selected);
            gameImage.setBackgroundResource(R.drawable.game_list_default);
            appTitle.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
            gameTitle.setTextColor(Tools.getColor(R.color.list_rank_default_color));
        }else if(view.getId() == R.id.gameLin){
            appImage.setBackgroundResource(R.drawable.app_list_default);
            gameImage.setBackgroundResource(R.drawable.game_list_selected);
            appTitle.setTextColor(Tools.getColor(R.color.list_rank_default_color));
            gameTitle.setTextColor(Tools.getColor(R.color.list_rank_selected_color));
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
