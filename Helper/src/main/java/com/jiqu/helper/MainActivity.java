package com.jiqu.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.jiqu.helper.data.RecommendData;
import com.jiqu.helper.fragments.AppFragment;
import com.jiqu.helper.fragments.GameFragment;
import com.jiqu.helper.fragments.MineFragment;
import com.jiqu.helper.fragments.RecommendFragment;
import com.jiqu.helper.fragments.ToolFragment;
import com.jiqu.helper.interfaces.GetDataCallback;
import com.jiqu.helper.okhttp.OkHttpManager;
import com.jiqu.helper.okhttp.OkHttpRequest;
import com.jiqu.helper.tools.RequestTools;
import com.jiqu.helper.view.HomeBottomView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseFragmentActivity {
    private HomeBottomView homeBottomView;
    private FragmentManager mFragmentManager;
    private RecommendFragment recommendFragment;
    private GameFragment gameFragment;
    private AppFragment appFragment;
    private MineFragment mineFragment;
    private ToolFragment toolFragment;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void init() {
        super.init();

        mFragmentManager = getSupportFragmentManager();
        recommendFragment = new RecommendFragment();
        gameFragment = new GameFragment();
        appFragment = new AppFragment();
        mineFragment = new MineFragment();
        toolFragment = new ToolFragment();

        fragments.add(recommendFragment);
        fragments.add(gameFragment);
        fragments.add(appFragment);
        fragments.add(mineFragment);
        fragments.add(toolFragment);
    }

    @Override
    public int getContentView() {
        return R.layout.main;
    }

    @Override
    public void initView() {
        homeBottomView = (HomeBottomView) findViewById(R.id.bottomView);
    }

    @Override
    public void initViewSize() {

    }

    @Override
    public void initData() {
        homeBottomView.setFragments(fragments);
        homeBottomView.setFragmentManager(mFragmentManager);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainer,recommendFragment);
        transaction.add(R.id.fragmentContainer,toolFragment);
        transaction.hide(toolFragment);
        transaction.commit();
    }
}