package com.jiqu.helper.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.GameChoiceData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.MineGameRecommendData;
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
import com.jiqu.helper.view.TitleBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/12.
 */
public class RecommendActivity extends BaseActivity implements View.OnClickListener,MyRecycleView.OnLoadDataListener{
    private final String OKHTTP_TAG = "RecommendActivity";
    private final String OKHTTP_TAG_DATA = "GameRankingFragmentData";
    private LinearLayout titleLin;
    private RelativeLayout contentRel;
    private TitleBar topBar;
    private String url;
    private String title;
    private String type = "";
    private int currentIndex = -1;
    private String baseUrl;
    private String loadUrl;
    private List<RecommendClassificationItemData> titleInfoList = new ArrayList<>();
    private List<List<GameInfo>> gameInfoList = new ArrayList<>();
    private List<RecommendAppAdapter> adapterList = new ArrayList<>();
    private List<MyRecycleView> recycleViewList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.recommend_layout;
    }

    @Override
    public void initView() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initUrl(getIntent().getStringExtra("type"));
        titleLin = (LinearLayout) findViewById(R.id.titleLin);
        contentRel = (RelativeLayout) findViewById(R.id.contentRel);
        topBar = (TitleBar) findViewById(R.id.topBar);

        topBar.setShareVisible(View.GONE);
    }

    private void initUrl(String type){
        this.type = type;
        if (!TextUtils.isEmpty(type)){
            switch (type){
                case "27"://游戏推荐
                    baseUrl = RequestTools.MINE_GAME_RECOMMEND_BASE;
                    break;
                case "29"://应用推荐
                case "32":
                    baseUrl = RequestTools.MINE_APP_RECOMMEND_BASE;
                    break;
            }
        }
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        topBar.setTitle(title);
        if (!"32".equals(type)){
            loadData();
        }else {
            RecommendClassificationItemData itemData = new RecommendClassificationItemData();
            itemData.setId("182");
            itemData.setName("软件");
            titleInfoList.add(itemData);
            RecommendClassificationItemData gameItemData = new RecommendClassificationItemData();
            gameItemData.setId("183");
            gameItemData.setName("游戏");
            titleInfoList.add(gameItemData);

            setTitleLinData(titleInfoList);
            setContentRelData(titleInfoList);
            titleLin.getChildAt(0).performClick();
        }
    }

    /** 获取类别 **/
    private void loadData(){
        if (!TextUtils.isEmpty(url)){
            OkHttpManager.getInstance().execute(new OkHttpRequest(url, OKHTTP_TAG, null, null).build(), MineGameRecommendData.class, new GetDataCallback() {
                @Override
                public void onFailed(Call call, IOException e) {

                }

                @Override
                public void onSucceed(Call call, Object data) {
                    MineGameRecommendData recommendData = (MineGameRecommendData) data;
                    if (recommendData != null){
                        if (recommendData.getStatus() == 1){
                            titleInfoList.clear();
                            titleInfoList.addAll(recommendData.getData());
                            setTitleLinData(recommendData.getData());
                            setContentRelData(titleInfoList);
                            titleLin.getChildAt(0).performClick();
                        }
                    }
                }
            });
        }
    }

    /** 获取具体分类数据 **/
    private void loadData(String url, final int index){
        OkHttpManager.getInstance().execute(new OkHttpRequest(url, OKHTTP_TAG_DATA, null, null).build(), GameChoiceData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                GameChoiceData choiceData = (GameChoiceData) data;
                if (choiceData != null){
                    if (choiceData.getStatus() == 1 && choiceData.getData() != null){
                        gameInfoList.get(index).addAll(choiceData.getData());
                        adapterList.get(index).notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void setTitleLinData(List<RecommendClassificationItemData> itemDataList){
        if (itemDataList != null && itemDataList.size() > 0){
            int size = itemDataList.size();
            for (int i = 0;i < size;i++){
                RecommendClassificationItemData data = itemDataList.get(i);
                MyImageButton button = new MyImageButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,(int)(MetricsTool.Rx * 120),1);
                button.setLayoutParams(params);
                button.setText(data.getName());
                button.setID(data.getId());
                button.setIndex(i);
                button.setOnClickListener(this);
                if (i == 0){
                    button.setSelected();
                }else{
                    TextView spaceView = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 2), (int)(MetricsTool.Rx * 60));
                    spaceView.setLayoutParams(layoutParams);
                    spaceView.setBackgroundColor(Tools.getColor(R.color.recommend_choice_space_color));
                    titleLin.addView(spaceView);
                }
                titleLin.addView(button);

            }
        }
    }

    private void setContentRelData(List<RecommendClassificationItemData> itemDataList){
        if (itemDataList != null && itemDataList.size() > 0){
            int size = itemDataList.size();
            for (int i = 0;i < size;i++){
                MyRecycleView recycleView = new MyRecycleView(this);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                manager.setOrientation(OrientationHelper.VERTICAL);
                recycleView.setLayoutManager(manager);
                recycleView.addItemDecoration(new SpaceItemDecoration(2,0));
                recycleView.setHasFixedSize(true);
                if (i != 0){
                    recycleView.setVisibility(View.GONE);
                }
                recycleView.setOnLoadDataListener(this);
                contentRel.addView(recycleView);
                recycleViewList.add(recycleView);
                List<GameInfo> infoList = new ArrayList<>();
                gameInfoList.add(infoList);
                final List<GameInfo> gameInfos = infoList;
                RecommendAppAdapter appAdapter = new RecommendAppAdapter(this,R.layout.recommend_app_item_layout,infoList);
                appAdapter.setListener(new RecycleViewOnItemClickListener() {
                    @Override
                    public void onItemClick(View view, BaseAdapter adapter, int position) {
                        Intent intent = new Intent(RecommendActivity.this, DetailActivity.class);
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
            }
        }
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
            int index = ((MyImageButton)v).getIndex();
            if (currentIndex != index){
                if (currentIndex != -1){
                    /** 取消上个请求 **/
                    recycleViewList.get(currentIndex).cancelLoad();
                }
                OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG_DATA);
                currentIndex = index;
                changeRecycleViewVisible(index);
                changeButtonState(v);
                if (gameInfoList.get(index).size() == 0){
                    url = baseUrl + titleInfoList.get(index).getId() + "&numPerPage=" + Constants.NUMBER_PER_PAGE;
                    loadData(url,index);
                }
            }
        }
    }

    @Override
    public void onLoadMore() {

    }
}
