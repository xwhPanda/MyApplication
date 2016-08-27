package com.jiqu.helper.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.DetailActivity;
import com.jiqu.helper.adapter.BaseAdapter;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.AppChoiceData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.interfaces.RecycleViewOnItemClickListener;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.AppFirstPublishItem;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/11.
 */
public class AppChoiceFragment extends BaseFragment implements View.OnClickListener,RecycleViewOnItemClickListener{
    private final String OKHTTP_TAG = "AppChoiceFragment";
    private View headView;
    private MyRecycleView recycleView;
    private RecommendAppAdapter appAdapter;
    private List<GameInfo> gameInfos = new ArrayList<>();
    private TextView title;
    private LinearLayout moreLin;
    private TextView moreText;
    private ImageView moreImg;
    private LinearLayout contentLin;
    private TextView appChoiceTitle;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.app_choice_layout,null);
        return view;
    }

    @Override
    public void initView() {
        initHeadView();
        appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,gameInfos);
        appAdapter.setListener(this);
        recycleView = (MyRecycleView) view.findViewById(R.id.recycleView);
        recycleView.addHeadView(headView);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.addItemDecoration(new SpaceItemDecoration(2,0));
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(appAdapter);
    }

    private void initHeadView(){
        headView = (View) LayoutInflater.from(mActivity).inflate(R.layout.app_choice_head_layout,null);
        title = (TextView) headView.findViewById(R.id.title);
        moreLin = (LinearLayout) headView.findViewById(R.id.moreLin);
        moreText = (TextView) headView.findViewById(R.id.moreText);
        moreImg = (ImageView) headView.findViewById(R.id.moreImg);
        contentLin = (LinearLayout) headView.findViewById(R.id.contentLin);
        appChoiceTitle = (TextView) headView.findViewById(R.id.appChoiceTitle);
        moreLin.setOnClickListener(this);
    }

    @Override
    public void initViewSize() {
        UIUtil.setTextSize(title,45);
        UIUtil.setTextSize(moreText,30);
        UIUtil.setTextSize(appChoiceTitle,45);
        UIUtil.setViewSize(moreImg, Rx * 48,Rx * 48);
        try {
            UIUtil.setViewSizeMargin(title,Rx * 60,Rx * 40,0,0);
            UIUtil.setViewSizeMargin(moreImg,0,0,Rx * 60,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.APP_CHOICE, OKHTTP_TAG, null, null).build(), AppChoiceData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                AppChoiceData choiceData = (AppChoiceData) data;
                if (choiceData != null){
                    if (choiceData.getStatus() == 1){
                        gameInfos.clear();
                        gameInfos.addAll(choiceData.getData2());
                        appAdapter.notifyDataSetChanged();
                        setFirstPublishApp(choiceData.getData1());
                    }
                }
            }
        });
    }

    private void setFirstPublishApp(List<GameInfo> infoList){
        int size = infoList.size();
        for (int i = 0;i < size;i++){
            AppFirstPublishItem item = new AppFirstPublishItem(mActivity);
            item.setData(infoList.get(i));
            if (i != 0){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 90), ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView view = new TextView(mActivity);
                view.setLayoutParams(params);
                contentLin.addView(view);

            }
            contentLin.addView(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.moreLin){

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
