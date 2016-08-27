package com.jiqu.helper.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.GameRankingData;
import com.jiqu.helper.data.GameRankingGameData;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.Constants;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.MyImageButton;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class GameRankingFragment extends BaseFragment implements View.OnClickListener,MyRecycleView.OnLoadDataListener{
    private final String OKHTTP_TAG_TITLE = "GameRankingFragmentTitle";
    private final String OKHTTP_TAG_DATA = "GameRankingFragmentData";
    private List<RecommendClassificationItemData> titleList = new ArrayList<>();
    private List<MyRecycleView> recycleViewList = new ArrayList<>();
    private int[] pageNums;
    private LinearLayout titleLin;
    private RelativeLayout recycleViewRel;
    private List<List<GameInfo>> gameInfoList = new ArrayList<>();
    private List<RecommendAppAdapter> adapterList = new ArrayList<>();
    private int currentIndex = -1;
    private String loadUrl;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.game_ranking_layout,null);
        return view;
    }

    @Override
    public void initView() {
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        recycleViewRel = (RelativeLayout) view.findViewById(R.id.recycleViewRel);
    }

    @Override
    public void initViewSize() {
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.GAME_RANKING_LIST,OKHTTP_TAG_TITLE,null,null).build(), GameRankingData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                GameRankingData rankingData = (GameRankingData) data;
                if (rankingData != null && rankingData.getStatus() == 1){
                    titleList.clear();
                    titleList.addAll(rankingData.getData());
                    setTitleView(titleList);
                    setContentView(titleList);
                    titleLin.getChildAt(0).performClick();
                }
            }
        });
    }

    private void setTitleView(List<RecommendClassificationItemData> dataList){
        int size = dataList.size();
        for (int i = 0;i < size;i++){
            RecommendClassificationItemData data = dataList.get(i);
            MyImageButton button = new MyImageButton(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,(int)(MetricsTool.Rx * 120),1);
            button.setLayoutParams(params);
            button.setText(data.getName());
            button.setID(data.getId());
            button.setIndex(i);
            button.setOnClickListener(this);
            if (i == 0){
                button.setSelected();
            }else{
                TextView spaceView = new TextView(mActivity);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 2), (int)(MetricsTool.Rx * 60));
                spaceView.setLayoutParams(layoutParams);
                spaceView.setBackgroundColor(Tools.getColor(R.color.recommend_choice_space_color));
                titleLin.addView(spaceView);
            }
            titleLin.addView(button);

        }
    }

    private void setContentView(List<RecommendClassificationItemData> dataList){
        int size = dataList.size();
        pageNums = new int[size];
        for (int i = 0;i < size;i++){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            MyRecycleView recycleView = new MyRecycleView(mActivity);
            recycleView.setLayoutParams(params);
            if (i != 0){
                recycleView.setVisibility(View.GONE);
            }
            LinearLayoutManager manager = new LinearLayoutManager(mActivity);
            manager.setOrientation(OrientationHelper.VERTICAL);
            recycleView.setLayoutManager(manager);
            recycleView.setHasFixedSize(true);
            recycleView.addItemDecoration(new SpaceItemDecoration(2,0));
            recycleView.setOnLoadDataListener(this);
            recycleViewRel.addView(recycleView);
            recycleViewList.add(recycleView);
            List<GameInfo> infoList = new ArrayList<>();
            gameInfoList.add(infoList);
            RecommendAppAdapter appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,infoList);

            final List<GameInfo> gameInfos = infoList;
            appAdapter.setListener(new RecycleViewOnItemClickListener() {
                @Override
                public void onItemClick(View view, BaseAdapter adapter, int position) {
                    Intent intent = new Intent(mActivity, DetailActivity.class);
                    if (Tools.getType(gameInfos.get(position).getType()) == -1) {
                        return;
                    } else {
                        intent.putExtra("type", Tools.getType(gameInfos.get(position).getType()));
                    }
                    intent.putExtra("id", gameInfos.get(position).getId());
                    intent.putExtra("name", gameInfos.get(position).getApply_name());
                    startActivity(intent);
                }
            });
            adapterList.add(appAdapter);
            recycleView.setAdapter(appAdapter);
            pageNums[i] = 1;
        }
    }

    private void loadData(String url, final int index){
        OkHttpManager.getInstance().execute(new OkHttpRequest(url, OKHTTP_TAG_DATA, null, null).build(),GameRankingGameData.class , new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                recycleViewList.get(index).loadComplete();
            }

            @Override
            public void onSucceed(Call call, Object data) {
                recycleViewList.get(index).loadComplete();
                GameRankingGameData gameData = (GameRankingGameData) data;
                if (gameData != null && gameData.getStatus() == 1){
                    gameInfoList.get(index).addAll(gameData.getData());
                    adapterList.get(index).notifyDataSetChanged();
                    pageNums[index]++;
                }else if (gameData != null && gameData.getStatus() == 0){
                    Tools.showToast(mActivity,R.string.load_no_more);
                }
            }
        });
    }

    private void changeRecycleViewVisible(int index){
        int size = recycleViewList.size();
        for (int i = 0;i < size;i++){
            if (index == i){
                recycleViewList.get(i).setVisibility(View.VISIBLE);
            }else {
                recycleViewList.get(i).setVisibility(View.GONE);
            }
        }
    }

    private void changeButtonState(View view){
        int childSize = titleLin.getChildCount();
        String id = ((MyImageButton) view).getID();
        for (int i = 0;i < childSize;i++){
            if (titleLin.getChildAt(i) instanceof MyImageButton){
                if (id.equals(((MyImageButton)(titleLin.getChildAt(i))).getID())){
                    ((MyImageButton)(titleLin.getChildAt(i))).setSelected();
                }else {
                    ((MyImageButton)(titleLin.getChildAt(i))).setDefault();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof MyImageButton){
            int index = ((MyImageButton) v).getIndex();
            if (currentIndex != index){
                if (currentIndex != -1){
                    recycleViewList.get(currentIndex).cancelLoad();
                }
                OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_DATA);
                currentIndex = index;
                changeRecycleViewVisible(index);
                changeButtonState(v);
                if (gameInfoList.get(index).size() == 0){
                    loadUrl = RequestTools.GAME_LIST + titleList.get(index).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE;
                    loadData(loadUrl,index);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        currentIndex = -1;
        recycleViewList.clear();
        recycleViewRel.removeAllViews();
        gameInfoList.clear();
        adapterList.clear();
        pageNums = null;
        titleLin.removeAllViews();
        titleList.clear();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_TITLE);
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_DATA);
    }

    @Override
    public void onLoadMore() {
        loadUrl = RequestTools.GAME_LIST + titleList.get(currentIndex).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE + "&pageNum=" + pageNums[currentIndex];
        loadData(loadUrl,currentIndex);
    }
}
