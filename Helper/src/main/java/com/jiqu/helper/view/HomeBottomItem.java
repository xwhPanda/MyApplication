package com.jiqu.helper.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public class HomeBottomItem extends RelativeLayout{
    private ImageView itemImg;
    private TextView itemName;
    private Fragment mFragment;

    public HomeBottomItem(Context context) {
        super(context);
        initView(context);
    }

    public HomeBottomItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.home_bottom_item,this);
        itemImg = (ImageView) view.findViewById(R.id.itemImg);
        itemName = (TextView) view.findViewById(R.id.itemName);

        initViewSize();
    }

    private void initViewSize(){
        UIUtil.setViewSize(itemImg, MetricsTool.Rx * 80,MetricsTool.Rx * 80);
        UIUtil.setTextSize(itemName,35);
    }

    public void setItemName(String name){
        itemName.setText(name);
    }

    public void setItemImg(int resId){
        itemImg.setBackgroundResource(resId);
    }

    /** 根据状态设置字体颜色以及更换图片 **/
    public void changeColorByState(boolean isSelected){
        if (isSelected){
            itemName.setTextColor(getResources().getColor(R.color.bottomItemNameSelectedColor));
        }else {
            itemName.setTextColor(getResources().getColor(R.color.bottomItemNameDefaultColor));
        }
    }

    /** 设置关联的fragment **/
    public void setFragment(Fragment fragment){
        mFragment = fragment;
    }

    /** 返回关联的fragment **/
    public Fragment getFragment(){
        return mFragment;
    }
}
