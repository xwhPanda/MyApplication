package com.jiqu.helper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/30.
 */
public class MyImageButton extends LinearLayout{
    private String ID = null;
    private int index = 0;
    private int textDefaultColor;
    private int textSelectedColor;
    private int bgDefaultColor;
    private int bgSelectedColor;
    private String text;
    private int imageDefault;
    private int imageSelected;
    private int textSize;
    private int imageWidth;
    private int imageHeight;
    private int space;
    private LinearLayout parent;
    private SimpleDraweeView image;
    private TextView title;
    private View bottomLine;

    public MyImageButton(Context context){
        super(context);
        initView(context,null);
    }

    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyImageButton);
            textDefaultColor = typedArray.getColor(R.styleable.MyImageButton_textColorDefault, Tools.getColor(R.color.black));
            textSelectedColor = typedArray.getColor(R.styleable.MyImageButton_textColorSelected,Tools.getColor(R.color.black));
            bgDefaultColor = typedArray.getColor(R.styleable.MyImageButton_bgColorDefault,Tools.getColor(R.color.white));
            bgSelectedColor = typedArray.getColor(R.styleable.MyImageButton_bgColorSelected,Tools.getColor(R.color.white));
            text = typedArray.getString(R.styleable.MyImageButton_text);
            imageDefault = typedArray.getResourceId(R.styleable.MyImageButton_imageDefault,0);
            imageSelected = typedArray.getResourceId(R.styleable.MyImageButton_imageSelected,imageDefault);
            textSize = typedArray.getInt(R.styleable.MyImageButton_textSize,40);
            imageHeight = typedArray.getInt(R.styleable.MyImageButton_imageHeight,70);
            imageWidth = typedArray.getInt(R.styleable.MyImageButton_imageWidth,70);
            space = typedArray.getInt(R.styleable.MyImageButton_verSpace,25);
            typedArray.recycle();
        }else {
            textSize = 40;
            imageWidth = 70;
            imageHeight = 70;
            space = 25;
            textDefaultColor = textSelectedColor = Tools.getColor(R.color.black);
            bgSelectedColor = bgDefaultColor = Tools.getColor(R.color.white);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.imagebutton_layout,this);
        image = (SimpleDraweeView) view.findViewById(R.id.image);
        title = (TextView) view.findViewById(R.id.title);
        bottomLine = (View) view.findViewById(R.id.bottomLine);
        parent = (LinearLayout) view.findViewById(R.id.parent);

        initViewSize();
    }

    private void initViewSize(){
        try {
            title.setText(text);
            title.setTextColor(textDefaultColor);
            image.setBackgroundResource(imageDefault);
            parent.setBackgroundColor(bgDefaultColor);

            UIUtil.setTextSize(title,textSize);
            UIUtil.setViewSizeMargin(title, MetricsTool.Rx * space,0,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBottomLineVisiable(int value){
        bottomLine.setVisibility(value);
    }

    public void setImage(String url){
        if (!TextUtils.isEmpty(url) && url.startsWith("http")){
            image.setImageURI(Uri.parse(url));
        }
    }

    public void setImage(int resId){
        image.setBackgroundResource(resId);
    }

    public void setTextSelectedColor(int color){
        textSelectedColor = color;
    }

    public void setTextDefaultColor(int color){
        textDefaultColor = color;
    }

    public void setText(String text){
        title.setText(text);
    }

    public void setID(String id){
        this.ID = id;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public String getID(){
        return this.ID;
    }

    public void setDefault(){
        title.setTextColor(textDefaultColor);
        image.setBackgroundResource(imageDefault);
        parent.setBackgroundColor(bgDefaultColor);
        bottomLine.setVisibility(GONE);
    }

    public void setSelected(){
        title.setTextColor(textSelectedColor);
        image.setBackgroundResource(imageSelected);
        parent.setBackgroundColor(bgSelectedColor);
        bottomLine.setVisibility(VISIBLE);
    }
}