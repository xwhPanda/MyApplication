package com.jiqu.helper.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiqu.helper.R;
import com.jiqu.helper.interfaces.OnChangTopViewVisible;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public class HomeBottomView extends RelativeLayout implements View.OnClickListener{

    private Context context;
    private HomeBottomItem recommendItem;
    private HomeBottomItem appItem;
    private HomeBottomItem gameItem;
    private HomeBottomItem accountItem;
    private HomeBottomItem toolItem;
    private List<HomeBottomItem> items = new ArrayList<>();
    private int lastIndex = 0;
    private int currentIndex = 0;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private OnChangTopViewVisible onChangTopViewVisible;

    public HomeBottomView(Context context) {
        super(context);
        initView(context);
    }

    public HomeBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /** 初始化view **/
    private void initView(Context context){
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.home_bottom_layout,this);
        recommendItem = (HomeBottomItem) view.findViewById(R.id.recommendItem);
        gameItem = (HomeBottomItem) view.findViewById(R.id.gameItem);
        appItem = (HomeBottomItem) view.findViewById(R.id.appItem);
        accountItem = (HomeBottomItem) view.findViewById(R.id.accountItem);
        toolItem = (HomeBottomItem) view.findViewById(R.id.toolItem);

        items.add(recommendItem);
        items.add(gameItem);
        items.add(appItem);
        items.add(accountItem);
        items.add(toolItem);

        recommendItem.setOnClickListener(this);
        appItem.setOnClickListener(this);
        gameItem.setOnClickListener(this);
        accountItem.setOnClickListener(this);
        toolItem.setOnClickListener(this);

        initData();
    }

    public void setOnChangTopViewVisible(OnChangTopViewVisible onChangTopViewVisible){
        this.onChangTopViewVisible = onChangTopViewVisible;
    }

    /** 设置fragments **/
    public void setFragments(List<Fragment> fragments){
        this.fragments = fragments;

        recommendItem.setFragment(fragments.get(0));
        gameItem.setFragment(fragments.get(1));
        appItem.setFragment(fragments.get(2));
        accountItem.setFragment(fragments.get(3));
        toolItem.setFragment(fragments.get(4));
    }

    /** 设置fragmentManager **/
    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    /** 初始化view的数据 **/
    private void initData(){
        recommendItem.setItemImg(R.mipmap.recommend_selected);
        recommendItem.setItemName(getResources().getString(R.string.recommend));
        recommendItem.changeColorByState(true);

        appItem.setItemImg(R.mipmap.app_default);
        appItem.setItemName(getResources().getString(R.string.app));

        accountItem.setItemImg(R.mipmap.mine_default);
        accountItem.setItemName(getResources().getString(R.string.account));

        gameItem.setItemImg(R.mipmap.game_default);
        gameItem.setItemName(context.getResources().getString(R.string.game));

        toolItem.setItemImg(R.mipmap.tool_default);
        toolItem.setItemName(context.getResources().getString(R.string.tool));
    }

    private void changeState(int lastIndex ,int currentIndex){
        switch (lastIndex){
            case 0:
                recommendItem.setItemImg(R.mipmap.recommend_default);
                recommendItem.changeColorByState(false);
                break;
            case 1:
                gameItem.setItemImg(R.mipmap.game_default);
                gameItem.changeColorByState(false);
                break;
            case 2:
                appItem.setItemImg(R.mipmap.app_default);
                appItem.changeColorByState(false);
                break;
            case 3:
                accountItem.setItemImg(R.mipmap.mine_default);
                accountItem.changeColorByState(false);
                break;
            case 4:
                toolItem.setItemImg(R.mipmap.tool_default);
                toolItem.changeColorByState(false);
                break;
        }

        switch (currentIndex){
            case 0:
                recommendItem.setItemImg(R.mipmap.recommend_selected);
                recommendItem.changeColorByState(true);
                break;
            case 1:
                gameItem.setItemImg(R.mipmap.game_selected);
                gameItem.changeColorByState(true);
                break;
            case 2:
                appItem.setItemImg(R.mipmap.app_selected);
                appItem.changeColorByState(true);
                break;
            case 3:
                accountItem.setItemImg(R.mipmap.mine_selected);
                accountItem.changeColorByState(true);
                break;
            case 4:
                toolItem.setItemImg(R.mipmap.tool_selected);
                toolItem.changeColorByState(true);
                break;
        }
    }

    private void clickEvent(HomeBottomItem item,int lastIndex,int index){
        if (onChangTopViewVisible != null){
            if (index == 3 || index == 4){
                onChangTopViewVisible.onChangeTopViewVisible(GONE);
            }else {
                onChangTopViewVisible.onChangeTopViewVisible(VISIBLE);
            }
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentIndex != index){
            transaction.hide(fragments.get(lastIndex));
            if (item.getFragment().isAdded()){
                transaction.show(item.getFragment());
            }else {
                transaction.add(R.id.fragmentContainer,item.getFragment());
            }
            transaction.commit();
        }
        currentIndex = index;
        if (lastIndex != currentIndex){
            changeState(lastIndex,currentIndex);
        }
        this.lastIndex = index;
    }

    @Override
    public void onClick(View view) {
        if (lastIndex != currentIndex){
            changeState(lastIndex,currentIndex);
        }
        switch (view.getId()){
            case R.id.recommendItem:
                clickEvent(recommendItem,lastIndex,0);
                break;
            case R.id.gameItem:
                clickEvent(gameItem,lastIndex,1);
                break;
            case R.id.appItem:
                clickEvent(appItem,lastIndex,2);
                break;
            case R.id.accountItem:
                clickEvent(accountItem,lastIndex,3);
                break;
            case R.id.toolItem:
                clickEvent(toolItem,lastIndex,4);
                break;
        }
    }
}
