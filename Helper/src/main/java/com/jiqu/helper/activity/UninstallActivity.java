package com.jiqu.helper.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.adapter.UninstallAdapter;
import com.jiqu.helper.data.InstalledApp;
import com.jiqu.helper.itemDecoration.SpaceItemDecoration;
import com.jiqu.helper.tools.FileUtil;
import com.jiqu.helper.tools.InstalledAppTool;
import com.jiqu.helper.view.MyImageButton;
import com.jiqu.helper.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/8/16.
 */
public class UninstallActivity extends BaseActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private TitleBar titleBar;
    private MyImageButton selfApp;
    private MyImageButton systemApp;
    private RecyclerView selfRecycleView;
    private RecyclerView systemRecycleView;
    private UninstallAdapter selfAdapter;
    private UninstallAdapter systemAdapter;
    private List<InstalledApp> selfAppList = new ArrayList<>();
    private List<InstalledApp> systemAppList = new ArrayList<>();
    private InstalledAppTool installedAppTool;
    private CheckBox oneKeyUninstallCheckBox;
    private Button oneKeyUninstall;
    private TextView spaceInfo;
    private ProgressBar progress;

    @Override
    public int getContentView() {
        return R.layout.uninstall_layout;
    }

    @Override
    public void initView() {
        installedAppTool = new InstalledAppTool();
        selfAdapter = new UninstallAdapter(this, R.layout.uninstall_item_layout, selfAppList);
        systemAdapter = new UninstallAdapter(this, R.layout.uninstall_item_layout, systemAppList);
        selfApp = (MyImageButton) findViewById(R.id.selfApp);
        systemApp = (MyImageButton) findViewById(R.id.systemApp);
        selfRecycleView = (RecyclerView) findViewById(R.id.selfRecycleView);
        systemRecycleView = (RecyclerView) findViewById(R.id.systemRecycleView);
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        oneKeyUninstallCheckBox = (CheckBox) findViewById(R.id.oneKeyUninstallCheckBox);
        oneKeyUninstall = (Button) findViewById(R.id.oneKeyUninstall);
        spaceInfo = (TextView) findViewById(R.id.spaceInfo);
        progress = (ProgressBar) findViewById(R.id.progress);

        titleBar.setShareVisible(View.INVISIBLE);
        titleBar.setTitle("应用卸载");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        selfRecycleView.setLayoutManager(manager);
        selfRecycleView.addItemDecoration(new SpaceItemDecoration(2, 0));
        selfRecycleView.setHasFixedSize(true);
        selfRecycleView.setAdapter(selfAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        systemRecycleView.setLayoutManager(layoutManager);
        systemRecycleView.addItemDecoration(new SpaceItemDecoration(2, 0));
        systemRecycleView.setHasFixedSize(true);
        systemRecycleView.setAdapter(systemAdapter);

        selfApp.setOnClickListener(this);
        systemApp.setOnClickListener(this);
        selfApp.setText("个人应用");
        systemApp.setText("系统应用");
        oneKeyUninstall.setOnClickListener(this);
        oneKeyUninstallCheckBox.setOnCheckedChangeListener(this);

        selfApp.performClick();

        spaceInfo.setText("已用" + Formatter.formatFileSize(this,FileUtil.getSDcardTotalSpace() - FileUtil.getSDcardAvailableSpace())
                            + ",剩余" + FileUtil.FormetFileSize(FileUtil.getSDcardAvailableSpace()));
        progress.setProgress((int)((FileUtil.getSDcardTotalSpace() - FileUtil.getSDcardAvailableSpace()) * 100 / FileUtil.getSDcardTotalSpace()));

    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        selfAppList.clear();
        selfAppList.addAll(installedAppTool.getPersonalApp(UninstallActivity.this));
        selfAdapter.notifyDataSetChanged();

        systemAppList.clear();
        systemAppList.addAll(installedAppTool.getSystemApp(UninstallActivity.this));
        systemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selfApp:
                selfApp.setSelected();
                systemApp.setDefault();
                systemRecycleView.setVisibility(View.GONE);
                selfRecycleView.setVisibility(View.VISIBLE);
                break;
            case R.id.systemApp:
                selfApp.setDefault();
                systemApp.setSelected();
                selfRecycleView.setVisibility(View.GONE);
                systemRecycleView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }
}
