package com.jiqu.helper.fragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.GameChoiceData;
import com.jiqu.helper.data.GameChoiceTopData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.RecommendChoiceAdInfo;
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
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.MyImageButton;
import com.jiqu.helper.view.MyRecycleView;
import com.jiqu.helper.view.NestedScrollingLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class GameChoiceFragment extends BaseFragment implements View.OnClickListener, MyRecycleView.OnLoadDataListener, RecycleViewOnItemClickListener {
    private final String OKHTTP_TAG = "GameChoiceFragment";
    private SimpleDraweeView adOne, adTwo;
    private LinearLayout titleLin;
    private MyRecycleView listView;
    private NestedScrollingLayout scrollingLayout;
    private RecommendAppAdapter appAdapter;
    private List<List<GameInfo>> gameInfoList = new ArrayList<>();
    private int[] indexList;
    private List<GameInfo> gameInfos = new ArrayList<>();
    private List<RecommendClassificationItemData> classificationInfoList = new ArrayList<>();
    private int currentIndex = 0;
    private String loadUrl = "";

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.game_choice_layout, null);
        return view;
    }

    @Override
    public void initView() {
        appAdapter = new RecommendAppAdapter(mActivity, R.layout.recommend_app_item_layout, gameInfos);
        appAdapter.setListener(this);
        scrollingLayout = (NestedScrollingLayout) view.findViewById(R.id.parent);
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        listView = (MyRecycleView) view.findViewById(R.id.listView);
        adOne = (SimpleDraweeView) view.findViewById(R.id.adOne);
        adTwo = (SimpleDraweeView) view.findViewById(R.id.adTwo);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        listView.setLayoutManager(manager);
        listView.setHasFixedSize(true);
        listView.addItemDecoration(new SpaceItemDecoration(2, 0));
        listView.setAdapter(appAdapter);
        listView.setOnLoadDataListener(this);
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewHeight(adOne, MetricsTool.Rx * 340);
        UIUtil.setViewHeight(adTwo, MetricsTool.Rx * 340);
        scrollingLayout.setHeadHeight((int) (MetricsTool.Rx * 340));
        UIUtil.setViewHeight(listView, MetricsTool.height - MetricsTool.Rx * 170 - MetricsTool.Rx * 74 - MetricsTool.Rx * 150 - MetricsTool.Rx * 115 - MetricsTool.Rx * 90);
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.GAME_CHOICE_AD, OKHTTP_TAG, null, null).build(),
                GameChoiceTopData.class, new GetDataCallback() {
                    @Override
                    public void onFailed(Call call, IOException e) {

                    }

                    @Override
                    public void onSucceed(Call call, Object data) {
                        GameChoiceTopData gameChoiceData = (GameChoiceTopData) data;
                        List<RecommendChoiceAdInfo> choiceAdInfos = gameChoiceData.getData1();
                        adOne.setImageURI(Uri.parse(choiceAdInfos.get(0).getRotate_pic()));
                        adTwo.setImageURI(Uri.parse(choiceAdInfos.get(1).getRotate_pic()));
                        setClassification(gameChoiceData.getData2());
                        loadUrl = RequestTools.GAME_CHOICE_BASE_URL
                                + gameChoiceData.getData2().get(0).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE;
                        loadData(loadUrl, 0);

                    }
                });
    }

    /**
     * 设置分类
     **/
    private void setClassification(List<RecommendClassificationItemData> data) {
        classificationInfoList.clear();
        classificationInfoList.addAll(data);
        int size = data.size();
        indexList = new int[size];
        for (int i = 0; i < size; i++) {
            RecommendClassificationItemData info = data.get(i);
            MyImageButton button = new MyImageButton(mActivity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, (int) (MetricsTool.Rx * 150), 1);
            button.setLayoutParams(layoutParams);
            button.setText(info.getName());
            button.setID(info.getId());
            button.setIndex(i);
            button.setOnClickListener(this);
            button.setImage(info.getPic());
            if (i != 0) {
                TextView spaceView = new TextView(mActivity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (MetricsTool.Rx * 2), (int) (MetricsTool.Rx * 75));
                spaceView.setLayoutParams(params);
                spaceView.setBackgroundColor(Tools.getColor(R.color.recommend_choice_space_color));
                titleLin.addView(spaceView);
            } else {
                button.setSelected();
            }
            titleLin.addView(button);

            List<GameInfo> infoList = new ArrayList<>();
            gameInfoList.add(infoList);
            indexList[i] = 1;
        }
    }

    /**
     * 请求数据
     **/
    public void loadData(String loadUrl, final int index) {
        OkHttpManager.getInstance().execute(new OkHttpRequest(loadUrl, "gameChoice", null, null).build(), GameChoiceData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                listView.loadComplete();
                Tools.showToast(mActivity, R.string.load_failed);
            }

            @Override
            public void onSucceed(Call call, Object data) {
                GameChoiceData gameChoiceData = (GameChoiceData) data;
                if (gameChoiceData != null && gameChoiceData.getStatus() == 1) {
                    indexList[index]++;
                    gameInfoList.get(index).addAll(gameChoiceData.getData());
                    gameInfos.clear();
                    gameInfos.addAll(gameInfoList.get(index));
                    appAdapter.notifyDataSetChanged();
                } else if (gameChoiceData != null && gameChoiceData.getStatus() == 0) {
                    /** 没有更多数据了 **/
                    Tools.showToast(mActivity, R.string.load_no_more);
                }
                listView.loadComplete();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view instanceof MyImageButton) {
            int index = ((MyImageButton) view).getIndex();
            String id = ((MyImageButton) view).getID();
            if (currentIndex != index) {
                OkHttpManager.getInstance().cancelByTag("gameChoice");
                listView.cancelLoad();
                int childSize = titleLin.getChildCount();
                for (int i = 0; i < childSize; i++) {
                    if (titleLin.getChildAt(i) instanceof MyImageButton) {
                        if (id.equals(((MyImageButton) (titleLin.getChildAt(i))).getID())) {
                            ((MyImageButton) (titleLin.getChildAt(i))).setSelected();
                        } else {
                            ((MyImageButton) (titleLin.getChildAt(i))).setDefault();
                        }
                    }
                }
                currentIndex = index;
                gameInfos.clear();
                gameInfos.addAll(gameInfoList.get(index));
                appAdapter.notifyDataSetChanged();
                loadUrl = RequestTools.GAME_CHOICE_BASE_URL
                        + classificationInfoList.get(index).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE;
                if (gameInfoList.get(index).size() == 0) {
                    loadData(loadUrl, index);
                }
            }
        }
    }

    @Override
    public void onLoadMore() {
        switch (currentIndex) {
            case 0:
                loadUrl = RequestTools.GAME_CHOICE_BASE_URL
                        + classificationInfoList.get(0).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE + "&pageNum=" + indexList[currentIndex];
                loadData(loadUrl, currentIndex);
                break;
        }
    }

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
}