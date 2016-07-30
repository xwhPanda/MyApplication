package com.jiqu.helper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/30.
 */
public class MyImageButton extends LinearLayout implements View.OnClickListener{
    private int textDefaultColor;
    private int textSelectedColor;
    private String text;
    private int imageDefault;
    private int imageSelected;
    private int textSize;
    private int imageWidth;
    private int imageHeight;
    private int space;
    private LinearLayout parent;
    private ImageView image;
    private TextView title;

    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyImageButton);
        textDefaultColor = typedArray.getColor(R.styleable.MyImageButton_textColorDefault, Tools.getColor(R.color.black));
        textSelectedColor = typedArray.getColor(R.styleable.MyImageButton_textColorSelected,Tools.getColor(R.color.black));
        text = typedArray.getString(R.styleable.MyImageButton_text);
        imageDefault = typedArray.getResourceId(R.styleable.MyImageButton_imageDefault,0);
        imageSelected = typedArray.getResourceId(R.styleable.MyImageButton_imageSelected,0);
        textSize = typedArray.getInt(R.styleable.MyImageButton_textSize,40);
        imageHeight = typedArray.getInt(R.styleable.MyImageButton_imageHeight,0);
        imageWidth = typedArray.getInt(R.styleable.MyImageButton_imageWidth,0);
        space = typedArray.getInt(R.styleable.MyImageButton_verSpace,5);

        View view = LayoutInflater.from(context).inflate(R.layout.imagebutton_layout,this);
        image = (ImageView) view.findViewById(R.id.image);
        title = (TextView) view.findViewById(R.id.title);
        parent = (LinearLayout) view.findViewById(R.id.parent);
        parent.setOnClickListener(this);

        initViewSize();
    }

    private void initViewSize(){
        title.setText(text);
        title.setTextColor(textDefaultColor);
        image.setBackgroundResource(imageDefault);

        UIUtil.setTextSize(title,textSize);
    }

    public void setDefault(){
        title.setTextColor(textDefaultColor);
        image.setBackgroundResource(imageDefault);
    }

    @Override
    public void onClick(View view) {
        title.setTextColor(textSelectedColor);
        image.setBackgroundResource(imageSelected);
    }
}