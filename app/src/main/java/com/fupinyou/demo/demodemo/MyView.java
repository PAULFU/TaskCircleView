package com.fupinyou.demo.demodemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by fupinyou on 2016/7/18.
 * blog address:http://www.fupinyou.com
 */
public class MyView extends RelativeLayout {
    private Drawable mLeftBackgroud;
    private String mLeftText;
    private int mLeftTextColor;
    private String mTitleText;
    private float mTitleSize;
    private int mTileTextColor;
    private int mRightTextColor;
    private String mRightText;
    private Drawable mRightBackground;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;
    private LayoutParams mRightParams;
    private LayoutParams mLeftParams;
    private LayoutParams mTitleParams;
    private topBarClickListener mListener;
    public static final int LEFT_BUTTUN=0;
    public static final int RIGHT_BUTTUN=1;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        initView(ta);
        addView(context);
        bindEvents();
    }

    private void initView(TypedArray ta){
        mLeftBackgroud = ta.getDrawable(R.styleable.MyView_leftBackground);
        mLeftText = ta.getString(R.styleable.MyView_leftText);
        mLeftTextColor = ta.getColor(R.styleable.MyView_leftTextColor, Color.BLACK);
        mTitleText = ta.getString(R.styleable.MyView_myTitle);
        mTitleSize = ta.getDimension(R.styleable.MyView_myTitleTextSize, 15);
        mTileTextColor = ta.getColor(R.styleable.MyView_myTitleTextColor, Color.BLACK);
        mRightTextColor = ta.getColor(R.styleable.MyView_rightTextColor, Color.BLACK);
        mRightText = ta.getString(R.styleable.MyView_rightText);
        mRightBackground = ta.getDrawable(R.styleable.MyView_rightBackground);
        //最后一定要调用recycle()来对ta进行回收，避免重新创建的错误
        ta.recycle();
    }

    private void addView(Context context) {
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组建元素赋值
        //值就来源于我们引用的ml文件中给对应属性的赋值
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackgroud);

        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);

        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTileTextColor);
        mTitleView.setTextSize(mTitleSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为每一个组件设置相应的布局参数
        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);


    }

    private interface topBarClickListener{
        //左边的点击事件
        void leftClick();
        //右边按钮的点击事件
        void rightClick();
    }

    private void bindEvents() {
        //按钮的点击事件不需要具体的实现，调用接口的时候，会有具体的实现
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.leftClick();
                }
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.rightClick();
                }
            }
        });
    }

    /**
     * 暴露一个接口供调用者来注册接口回调
     * 通过接口来获得回调这对接口方法的实现
     */
    public void setOnTopbarClickListener(topBarClickListener mListener){
        this.mListener=mListener;
    }


    /**
     * 设置按钮的显示与否，通过id区分按钮，flag区分是否显示
     */
    public void setButtonVisable(int ChidView,boolean flag){
        switch (ChidView){
            case LEFT_BUTTUN:
                if (flag==false){
                    mLeftButton.setVisibility(View.VISIBLE);
                }else{
                    mLeftButton.setVisibility(View.GONE);
                }
                break;
            case RIGHT_BUTTUN:
                if (flag==false){
                    mRightButton.setVisibility(View.VISIBLE);
                }else{
                    mRightButton.setVisibility(View.GONE);
                }
                break;
        }
    }
}
