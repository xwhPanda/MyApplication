package com.jiqu.helper.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiqu.helper.BaseFragment;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.DetailCommentAdapter;
import com.jiqu.helper.data.CommentData;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.Constants;
import com.jiqu.helper.tools.FullyLinearLayoutManager;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.tools.UIUtil;
import com.jiqu.helper.view.MyRecycleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/8/9.
 */
public class DetailCommentFragment extends BaseFragment{
    private final String OKHTTP_TAG = "DetailCommentFragment";
    private String id;
    private RecyclerView commentRecyclerView;
    private DetailCommentAdapter adapter;
    private List<CommentData.CommentInfo> commentInfoList = new ArrayList<>();
    private TextView commentCount;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.detail_comment_layout,null);
        return view;
    }

    @Override
    public void initView() {
        id = getArguments().getString("id");
        adapter = new DetailCommentAdapter(mActivity,R.layout.detail_comment_item_layout,commentInfoList);
        commentRecyclerView = (RecyclerView) view.findViewById(R.id.commentRecyclerView);
        commentCount = (TextView) view.findViewById(R.id.commentCount);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(OrientationHelper.VERTICAL);
        commentRecyclerView.setLayoutManager(manager);
        commentRecyclerView.setHasFixedSize(true);
        commentRecyclerView.addItemDecoration(new SpaceItemDecoration(2,0));
        commentRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initViewSize() {
        UIUtil.setViewHeight(commentRecyclerView, MetricsTool.height - Rx * 170 - Rx  * 120 - Rx  * 135);
    }

    @Override
    public void initData() {
        if (TextUtils.isEmpty(id))
            return;
        OkHttpManager.getInstance().execute(new OkHttpRequest(RequestTools.APP_DETAIL_COMMENT + id + "&numPerPage=" + Constants.NUMBER_PER_PAGE, OKHTTP_TAG, null, null).build(), CommentData.class, new GetDataCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSucceed(Call call, Object data) {
                CommentData commentData = (CommentData) data;
                if (commentData != null){
                    if (commentData.getStatus() == 1 && commentData.getData() != null){
                        commentInfoList.clear();
                        commentInfoList.addAll(commentData.getData());
                        adapter.notifyDataSetChanged();
                    }
                    commentCount.setText("(" + commentData.getCount() + "条)");
                }
            }
        });
    }
}
