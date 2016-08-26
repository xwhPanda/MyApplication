package com.jiqu.helper.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.adapter.SearchAdapter;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.SearchContentData;
import com.jiqu.helper.data.SearchData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/19.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener{
    private final String OKHTTP_TAG = "SearchActivity";
    private final String OKHTTP_SEARCH_TAG = "SearchActivityData";
    private LinearLayout contentLin;
    private RecyclerView recycleView;
    private MyRecycleView contentRecyclerView;
    private List<SearchData.SearchInfo> searchInfoList = new ArrayList<>();
    private SearchAdapter adapter;
    private ImageButton backBtn;
    private ImageButton searchBtn;
    private EditText searchEdit;
    private LinearLayout linearLayout;
    private LinearLayout searchLin;
    private RecommendAppAdapter appAdapter;
    private List<GameInfo> gameInfoList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.search_layout;
    }

    @Override
    public void initView() {
        adapter = new SearchAdapter(this,R.layout.search_item_layout,searchInfoList);
        appAdapter = new RecommendAppAdapter(this,R.layout.recommend_app_item_layout,gameInfoList);
        contentLin = (LinearLayout) findViewById(R.id.contentLin);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        searchBtn = (ImageButton) findViewById(R.id.searchBtn);
        contentRecyclerView = (MyRecycleView) findViewById(R.id.contentRecyclerView);
        searchEdit = (EditText) findViewById(R.id.searchEdit);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        searchLin = (LinearLayout) findViewById(R.id.searchLin);

        backBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        contentRecyclerView.setLayoutManager(layoutManager);
        contentRecyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        contentRecyclerView.setHasFixedSize(true);
        contentRecyclerView.setAdapter(appAdapter);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.SEARCH, OKHTTP_TAG, null, null).build(), SearchData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                SearchData searchData = (SearchData) data;
                if (searchData != null){
                    if (searchData.getStatus() == 1){
                        setContentData(searchData.getData());
                        searchInfoList.clear();
                        if (searchData.getData() != null && searchData.getData().size() == 3){
                            searchInfoList.addAll(searchData.getData().get(2));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    private void setContentData(List<List<SearchData.SearchInfo>> data){
        int size = data.size();
        for (int i = 0;i < size - 1;i++){
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            layoutParams.topMargin = 80;
            if (i == size -2){
                layoutParams.bottomMargin = 80;
            }
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setGravity(Gravity.CENTER);
            contentLin.addView(linearLayout);
            List<SearchData.SearchInfo> searchInfoList = data.get(i);
            TextView textView = new TextView(this);
            if (i == 0)
                textView.setText("游戏");
            else
                textView.setText("应用");
            textView.setTextColor(Tools.getColor(R.color.black));
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
            int length = searchInfoList.size();
            for (int j = 0;j < length;j++){
                Button button = new Button(this);
                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(MetricsTool.dip2px(this,85),MetricsTool.dip2px(this,30));
                btnParams.leftMargin = 45;
                button.setLayoutParams(btnParams);
                button.setPadding(0,0,0,0);
                button.setText(searchInfoList.get(j).getContent());
                button.setTextColor(Tools.getColor(R.color.recommend_choice_game_detail_color));
                button.setTextSize(TypedValue.COMPLEX_UNIT_PX, MetricsTool.dip2px(this,12));
                button.setBackgroundResource(R.mipmap.blue_btn_bg);
                linearLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.searchBtn:
                OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
                linearLayout.setVisibility(View.GONE);
                searchLin.setVisibility(View.VISIBLE);
                search();
                break;
        }
    }

    private void search(){
        String searchContent = searchEdit.getText().toString();
        String url = RequestTools.SEARCH_DATA + searchContent + "&pageNum=1&numPerPage=10";
        if (!TextUtils.isEmpty(searchContent)){
            OkHttpManager.getInstance().execute(new OkHttpRequest(url,OKHTTP_SEARCH_TAG,null,null).build(), SearchContentData.class, new GetDataCallback() {
                @Override
                public void onFailed(Call call, IOException e) {

                }

                @Override
                public void onSucceed(Call call, Object data) {
                    SearchContentData contentData = (SearchContentData) data;
                    if (contentData != null){
                        if (contentData.getStatus() == 1 && contentData.getData() != null){
                            gameInfoList.clear();
                            gameInfoList.addAll(contentData.getData());
                            appAdapter.notifyDataSetChanged();
                        }else if (contentData.getStatus() == 0){
                            Tools.showToast(SearchActivity.this,"没有相关应用");
                        }
                    }
                }
            });
        } else {
            Tools.showToast(this,"请输入要搜索的内容");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpManager.getInstance().cancelByTag(OKHTTP_TAG);
        OkHttpManager.getInstance().cancelByTag(OKHTTP_SEARCH_TAG);
    }
}
