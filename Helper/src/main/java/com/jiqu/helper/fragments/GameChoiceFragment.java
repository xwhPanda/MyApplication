package com.jiqu.helper.fragments;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.RecommendAppAdapter;
import com.jiqu.helper.data.GameChoiceData;
import com.jiqu.helper.data.GameChoiceTopData;
import com.jiqu.helper.data.GameInfo;
import com.jiqu.helper.data.RecommendChoiceAdInfo;
import com.jiqu.helper.data.RecommendClassificationInfo;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.MyImageButton;
import com.jiqu.helper.view.PullUpListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class GameChoiceFragment extends BaseFragment implements View.OnClickListener{
    private final String OKHTTP_TAG = "GameChoiceFragment";
    private SimpleDraweeView adOne,adTwo;
    private LinearLayout titleLin;
    private PullUpListView listView;
    private RecommendAppAdapter appAdapter;
    private List<GameInfo> gameInfos = new ArrayList<>();

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.game_choice_layout,null);
        return view;
    }

    @Override
    public void initView() {
        appAdapter = new RecommendAppAdapter(mActivity,R.layout.recommend_app_item_layout,gameInfos);
        titleLin = (LinearLayout) view.findViewById(R.id.titleLin);
        listView = (PullUpListView) view.findViewById(R.id.listView);
        adOne = (SimpleDraweeView) view.findViewById(R.id.adOne);
        adTwo = (SimpleDraweeView) view.findViewById(R.id.adTwo);
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewHeight(adOne, MetricsTool.Rx * 340);
        UIUtil.setViewHeight(adTwo, MetricsTool.Rx * 340);
        UIUtil.setViewHeight(listView,MetricsTool.height - MetricsTool.Rx * 170 - MetricsTool.Rx * 74 - MetricsTool.Rx * 150 - MetricsTool.Rx * 115);
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.GAME_CHOICE_AD,OKHTTP_TAG,null,null).build(),
                GameChoiceTopData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                GameChoiceTopData gameChoiceData = (GameChoiceTopData)data;
                List<RecommendChoiceAdInfo> choiceAdInfos = gameChoiceData.getData1();
                adOne.setImageURI(Uri.parse(choiceAdInfos.get(0).getRotate_pic()));
                adTwo.setImageURI(Uri.parse(choiceAdInfos.get(1).getRotate_pic()));
                setClassification(gameChoiceData.getData2());
                loadData(RequestTools.GAME_CHOICE_BASE_URL + gameChoiceData.getData2().get(0).getId(),"choice");
            }
        });
    }

    /** 设置分类 **/
    private void setClassification(List<RecommendClassificationInfo> data){
        int size = data.size();
        for (int i = 0;i < size;i++){
            RecommendClassificationInfo info = data.get(i);
            MyImageButton button = new MyImageButton(mActivity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, (int)(MetricsTool.Rx * 150), 1);
            button.setLayoutParams(layoutParams);
            button.setText(info.getName());
            button.setID(info.getId());
            button.setIndex(i);
            button.setOnClickListener(this);
            button.setImage(info.getPic());
            if (i != 0){
                TextView spaceView = new TextView(mActivity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(MetricsTool.Rx * 2), ViewGroup.LayoutParams.MATCH_PARENT);
                spaceView.setLayoutParams(params);
                titleLin.addView(spaceView);
            }
            titleLin.addView(button);
        }
    }

    /** 请求数据 **/
    public void loadData(String loadUrl,String tag){
        OkHttpManager.getInstance().execute(new OkHttpRequest(loadUrl, tag, null, null).build(), GameChoiceData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                GameChoiceData gameChoiceData = (GameChoiceData) data;
                if (gameChoiceData != null && gameChoiceData.getStatus() == 1){
                    gameInfos.clear();
                    gameInfos.addAll(gameChoiceData.getData());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view instanceof MyImageButton){
            int index = ((MyImageButton) view).getIndex();
            switch (index){
                case 0:
                    break;
            }
        }
    }
}
