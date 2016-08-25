package com.jiqu.helper.fragments;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.activity.AccountInfoActivity;
import com.jiqu.helper.activity.RecommendActivity;
import com.jiqu.helper.adapter.RecommendClassificationItemAdapter;
import com.jiqu.helper.data.MineData;
import com.jiqu.helper.data.RecommendClassificationItemData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.CircleImageView;
import com.jiqu.helper.view.HeaderGridView;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/5.
 */
public class MineFragment extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener{
    private final String OKHTTP_TAG = "MineFragment";
    private TextView appName;
    private Button message;
    private CircleImageView accountImg;
    private TextView lastLoginTime;
    private TextView account;
    private HeaderGridView gridView;
    private RecommendClassificationItemAdapter adapter;
    private List<RecommendClassificationItemData> infoList = new ArrayList<>();
    private View headView;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.mine,null);
        return view;
    }

    @Override
    public void initView() {
        initHeadView();
        adapter = new RecommendClassificationItemAdapter(mActivity,infoList);
        gridView = (HeaderGridView) view.findViewById(R.id.gridView);

        gridView.addHeaderView(headView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    private void initHeadView(){
        headView = LayoutInflater.from(mActivity).inflate(R.layout.mine_head_layout,null);
        appName = (TextView) headView.findViewById(R.id.appName);
        message = (Button) headView.findViewById(R.id.message);
        accountImg = (CircleImageView) headView.findViewById(R.id.accountImg);
        lastLoginTime = (TextView) headView.findViewById(R.id.lastLoginTime);
        account = (TextView) headView.findViewById(R.id.account);

        accountImg.setOnClickListener(this);
    }

    @Override
    public void initViewSize() {
        UIUtil.setTextSize(appName,45);
        UIUtil.setTextSize(account,50);
        UIUtil.setTextSize(lastLoginTime,40);
        UIUtil.setViewSize(message,Rx * 97,Rx * 97);
    }

    @Override
    public void initData() {
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.MINE_CLASSIFICATION, OKHTTP_TAG, null, null).build(), MineData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                MineData mineData = (MineData) data;
                if (mineData != null){
                    if (mineData.getStatus() == 1){
                        infoList.clear();
                        infoList.addAll(mineData.getData());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = "";
        String type = "";
        String idValue = infoList.get(position - gridView.getNumColumns()).getId();
        if ("27".equals(idValue)){
            url = RequestTools.MINE_GAME_RECOMMEND;
            type = "27";
        }else if ("29".equals(idValue)){
            url = RequestTools.MINE_APP_RECOMMEND;
            type = "29";
        }else if("32".equals(idValue)){
            type = "32";
        }
        if (!TextUtils.isEmpty(type)){
            Intent intent = new Intent(mActivity, RecommendActivity.class);
            intent.putExtra("url",url);
            intent.putExtra("type",type);
            intent.putExtra("title",infoList.get(position - gridView.getNumColumns()).getName());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.accountImg){
            startActivity(new Intent(mActivity, AccountInfoActivity.class));
        }
    }
}
