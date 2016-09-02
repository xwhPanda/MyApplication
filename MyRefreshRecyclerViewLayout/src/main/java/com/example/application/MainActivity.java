package com.example.application;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xwh.interfaces.OnRefreshListener;
import com.xwh.view.RefreshRecyclerViewLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRefreshListener {
    private RecyclerView recyclerView,recyclerView1;
    private RefreshRecyclerViewLayout layout;
    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initData();

        layout = (RefreshRecyclerViewLayout) findViewById(R.id.layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);

        layout.setOnRefreshListener(this);

        adapter = new MyAdapter(this,list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(adapter);
    }

    private void initData(){
        for (int i = 0;i < 20;i++){
            list.add("这是第 " + i + "项 ！");
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            layout.refreshCompleted();
            super.handleMessage(msg);
        }
    };

    @Override
    public void startRefresh() {
//        handler.sendEmptyMessageDelayed(1,3000);
    }

    @Override
    public void refreshing() {
        Toast.makeText(this,"正在刷新",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshFail() {

    }

    @Override
    public void refreshComplete() {

    }
}
