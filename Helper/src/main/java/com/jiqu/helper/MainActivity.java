package com.jiqu.helper;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.jiqu.database.Account;
import com.jiqu.helper.activity.RegisterActivity;
import com.jiqu.helper.activity.SearchActivity;
import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.fragments.AppFragment;
import com.jiqu.helper.fragments.GameFragment;
import com.jiqu.helper.fragments.MineFragment;
import com.jiqu.helper.fragments.RecommendFragment;
import com.jiqu.helper.fragments.ToolFragment;
import com.jiqu.helper.interfaces.OnChangTopViewVisible;
import com.jiqu.helper.view.HomeBottomView;
import com.jiqu.helper.view.HomeTopView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements OnChangTopViewVisible {
    private HomeBottomView homeBottomView;
    private HomeTopView topView;
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
        topView = (HomeTopView) findViewById(R.id.topView);
        homeBottomView = (HomeBottomView) findViewById(R.id.bottomView);

        homeBottomView.setOnChangTopViewVisible(this);

        topView.setSearchEditListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        topView.setAccountListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity();
            }
        });
    }

    private void gotoActivity(){
        Account account = HelperApplication.daoSession.getAccountDao().queryBuilder().unique();
        if (account != null){
            Log.i("TAG","username :　" + account.getUsername() + " / " + account.getNickname());
        }else {
            startActivity(new Intent(this,RegisterActivity.class));
            Log.i("TAG","account is null !");
        }
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

    @Override
    public void onChangeTopViewVisible(int visible) {
        topView.setVisibility(visible);
    }
}