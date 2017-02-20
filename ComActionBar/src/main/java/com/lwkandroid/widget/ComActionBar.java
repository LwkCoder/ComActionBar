package com.lwkandroid.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by LWK
 * TODO 通用自定义ActionBar
 * 2016/11/10
 */
public class ComActionBar extends FrameLayout
{
    protected View mViewLeft;
    protected TextView mTvLeft;
    protected ImageView mImgLeft;
    protected TextView mTvTitle;
    protected View mViewRight01;
    protected View mViewRight02;
    protected TextView mTvRight01;
    protected TextView mTvRight02;
    protected ImageView mImgRight01;
    protected ImageView mImgRight02;

    public ComActionBar(Context context)
    {
        super(context);
        init(context, null);
    }

    public ComActionBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    //初始化
    private void init(Context context, AttributeSet attrs)
    {
        inflate(context, R.layout.layout_comactionbar, this);
        setWillNotDraw(false);
        mViewLeft = findViewById(R.id.fl_comactionbar_left);
        mViewRight01 = findViewById(R.id.fl_comactionbar_right01);
        mViewRight02 = findViewById(R.id.fl_comactionbar_right02);
        initParams(context, attrs);
    }

    //初始化自定义属性
    private void initParams(Context context, AttributeSet attrs)
    {
        boolean isLeftAsBack = false;//左侧是否为返回
        String leftText = null;//左侧文案
        int leftImageResId = 0;//左侧图片
        String title = null;//标题
        String rightText01 = null;//右边第一个文案
        int rightImageResId01 = 0;//右边第一个图片
        String rightText02 = null;//右边第二个文案
        int rightImageResId02 = 0;//右边第二个图片
        int titleTextColor = Color.WHITE;//标题文字颜色
        int leftTextColor = Color.WHITE;//左边文字颜色
        int rightTextColor01 = Color.WHITE;//右边第一个文字颜色
        int rightTextColor02 = Color.WHITE;//右边第二个文字颜色
        int bgColor = context.getApplicationContext().getResources().getColor(R.color.colorPrimary);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ComActionBar);
        if (ta != null)
        {
            int count = ta.getIndexCount();
            for (int i = 0; i < count; i++)
            {
                int index = ta.getIndex(i);
                if (index == R.styleable.ComActionBar_bg_color)
                    bgColor = ta.getColor(index, bgColor);
                else if (index == R.styleable.ComActionBar_is_left_as_back)
                    isLeftAsBack = ta.getBoolean(index, false);
                else if (index == R.styleable.ComActionBar_left_text)
                    leftText = ta.getString(index);
                else if (index == R.styleable.ComActionBar_left_image)
                    leftImageResId = ta.getResourceId(index, 0);
                else if (index == R.styleable.ComActionBar_title_text)
                    title = ta.getString(index);
                else if (index == R.styleable.ComActionBar_right_text01)
                    rightText01 = ta.getString(index);
                else if (index == R.styleable.ComActionBar_right_image01)
                    rightImageResId01 = ta.getResourceId(index, 0);
                else if (index == R.styleable.ComActionBar_right_text02)
                    rightText02 = ta.getString(index);
                else if (index == R.styleable.ComActionBar_right_image02)
                    rightImageResId02 = ta.getResourceId(index, 0);
                else if (index == R.styleable.ComActionBar_title_text_color)
                    titleTextColor = ta.getColor(index, Color.WHITE);
                else if (index == R.styleable.ComActionBar_left_text_color)
                    leftTextColor = ta.getColor(index, Color.WHITE);
                else if (index == R.styleable.ComActionBar_right_text_color01)
                    rightTextColor01 = ta.getColor(index, Color.WHITE);
                else if (index == R.styleable.ComActionBar_right_text_color02)
                    rightTextColor02 = ta.getColor(index, Color.WHITE);
            }
            ta.recycle();
        }

        setBackgroundColor(bgColor);

        if (isLeftAsBack)
        {
            setLeftAsBack();
            setLeftTextColor(leftTextColor);
        } else if (!TextUtils.isEmpty(leftText))
        {
            setLeftText(leftText);
            setLeftTextColor(leftTextColor);
        }

        if (leftImageResId != 0)
            setLeftImage(leftImageResId);

        if (!TextUtils.isEmpty(title))
        {
            setTitle(title);
            setTitleTextColor(titleTextColor);
        }

        if (!TextUtils.isEmpty(rightText01))
        {
            setRightText01(rightText01);
            setRightTextColor01(rightTextColor01);
        }

        if (rightImageResId01 != 0)
            setRightImage01(rightImageResId01);

        if (!TextUtils.isEmpty(rightText02))
        {
            setRightText02(rightText02);
            setRightTextColor02(rightTextColor02);
        }

        if (rightImageResId02 != 0)
            setRightImage02(rightImageResId02);
    }

    //引用左侧textview
    private void inflateLeftTextView()
    {
        if (mTvLeft == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_left_text)).inflate();
            mTvLeft = (TextView) findViewById(R.id.tv_comactionbar_left);
        }
    }

    /**
     * 设置左边为返回功能
     */
    public void setLeftAsBack()
    {
        inflateLeftTextView();
        Drawable drawable = getResources().getDrawable(R.drawable.icon_arrow_left);
        int width = getResources().getDimensionPixelSize(R.dimen.cab_back_drawable_width);
        int height = getResources().getDimensionPixelSize(R.dimen.cab_back_drawable_height);
        drawable.setBounds(0, 0, width, height);
        mTvLeft.setCompoundDrawables(drawable, null, null, null);
        mTvLeft.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.cab_back_drawable_padding));
        if (TextUtils.isEmpty(mTvLeft.getText()))//没有设置文案就默认给“返回”
            mTvLeft.setText(R.string.cab_back);
        if (getContext() instanceof Activity)
        {
            setLeftClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ((Activity) getContext()).finish();
                }
            });
        }
    }

    /**
     * 设置左侧文案
     */
    public void setLeftText(String text)
    {
        inflateLeftTextView();
        mTvLeft.setText(text);
    }

    /**
     * 设置左侧文案
     */
    public void setLeftText(int resId)
    {
        inflateLeftTextView();
        mTvLeft.setText(resId);
    }

    /**
     * 设置左侧文案文字颜色
     *
     * @param color
     */
    public void setLeftTextColor(int color)
    {
        inflateLeftTextView();
        mTvLeft.setTextColor(color);
    }

    //引用左侧ImageView
    private void inflateLeftImage()
    {
        if (mImgLeft == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_left_image)).inflate();
            mImgLeft = (ImageView) findViewById(R.id.img_comactionbar_left);
        }
    }

    /**
     * 设置左侧图片
     */
    public void setLeftImage(int imgResId)
    {
        inflateLeftImage();
        mImgLeft.setImageResource(imgResId);
    }

    /**
     * 设置左侧图片
     */
    public void setLeftImage(Drawable drawable)
    {
        inflateLeftImage();
        mImgLeft.setImageDrawable(drawable);
    }

    /**
     * 设置左侧图片
     */
    public void setLeftImage(Bitmap bitmap)
    {
        inflateLeftImage();
        mImgLeft.setImageBitmap(bitmap);
    }

    /**
     * 设置左侧点击事件
     * R.id.fl_comactionbar_left
     */
    public void setLeftClickListener(OnClickListener listener)
    {
        mViewLeft.setOnClickListener(listener);
    }

    /**
     * 设置左侧背景
     */
    public void setLeftBackground(int resId)
    {
        mViewLeft.setBackgroundResource(resId);
    }

    //引用标题TextView
    private void inflateTitleTextView()
    {
        if (mTvTitle == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_title)).inflate();
            mTvTitle = (TextView) findViewById(R.id.tv_comactionbar_title);
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(int resId)
    {
        inflateTitleTextView();
        mTvTitle.setText(resId);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title)
    {
        inflateTitleTextView();
        mTvTitle.setText(title);
    }

    /**
     * 设置标题文字颜色
     *
     * @param color
     */
    public void setTitleTextColor(int color)
    {
        inflateTitleTextView();
        mTvTitle.setTextColor(color);
    }

    //引用右边第一个TextView
    private void inflateRightTextView01()
    {
        if (mTvRight01 == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_right_text01)).inflate();
            mTvRight01 = (TextView) findViewById(R.id.tv_comactionbar_right01);
        }
    }

    /**
     * 设置右边第一个文案
     */
    public void setRightText01(int resId)
    {
        inflateRightTextView01();
        mTvRight01.setText(resId);
    }

    /**
     * 设置右边第一个文案
     */
    public void setRightText01(String text)
    {
        inflateRightTextView01();
        mTvRight01.setText(text);
    }

    /**
     * 设置右边第一个文案文字颜色
     *
     * @param color
     */
    public void setRightTextColor01(int color)
    {
        inflateRightTextView01();
        mTvRight01.setTextColor(color);
    }

    //引用右边第二个TextView
    private void inflateRightTextView02()
    {
        if (mTvRight02 == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_right_text02)).inflate();
            mTvRight02 = (TextView) findViewById(R.id.tv_comactionbar_right02);
        }
    }

    /**
     * 设置右边第二个文案
     */
    public void setRightText02(int resId)
    {
        inflateRightTextView02();
        mTvRight02.setText(resId);
    }

    /**
     * 设置右边第二个文案
     */
    public void setRightText02(String text)
    {
        inflateRightTextView02();
        mTvRight02.setText(text);
    }

    /**
     * 设置右边第二个文案文字颜色
     *
     * @param color
     */
    public void setRightTextColor02(int color)
    {
        inflateRightTextView02();
        mTvRight02.setTextColor(color);
    }

    //引用右边第一个ImageView
    private void inflateRightImageView01()
    {
        if (mImgRight01 == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_right_image01)).inflate();
            mImgRight01 = (ImageView) findViewById(R.id.img_comactionbar_right01);
        }
    }

    /**
     * 设置右边第一个图片
     */
    private void setRightImage01(int resId)
    {
        inflateRightImageView01();
        mImgRight01.setImageResource(resId);
    }

    /**
     * 设置右边第一个图片
     */
    private void setRightImage01(Drawable drawable)
    {
        inflateRightImageView01();
        mImgRight01.setImageDrawable(drawable);
    }

    /**
     * 设置右边第一个图片
     */
    private void setRightImage01(Bitmap bitmap)
    {
        inflateRightImageView01();
        mImgRight01.setImageBitmap(bitmap);
    }

    /**
     * 设置右边第一个区域背景
     */
    public void setRightBackgroud01(int resId)
    {
        mViewRight01.setBackgroundResource(resId);
    }

    /**
     * 设置右边第一个区域点击事件
     * R.id.fl_comactionbar_right01
     */
    public void setRightClickListener01(OnClickListener listener)
    {
        mViewRight01.setOnClickListener(listener);
    }

    //引用右边第二个ImageView
    private void inflateRightImageView02()
    {
        if (mImgRight02 == null)
        {
            ((ViewStub) findViewById(R.id.vs_comactionbar_right_image02)).inflate();
            mImgRight02 = (ImageView) findViewById(R.id.img_comactionbar_right02);
        }
    }

    /**
     * 设置右边第二个图片
     */
    private void setRightImage02(int resId)
    {
        inflateRightImageView02();
        mImgRight02.setImageResource(resId);
    }

    /**
     * 设置右边第二个图片
     */
    private void setRightImage02(Drawable drawable)
    {
        inflateRightImageView02();
        mImgRight02.setImageDrawable(drawable);
    }

    /**
     * 设置右边第二个图片
     */
    private void setRightImage02(Bitmap bitmap)
    {
        inflateRightImageView01();
        mImgRight02.setImageBitmap(bitmap);
    }

    /**
     * 设置第二个区域背景
     */
    public void setRightBackgroud02(int resId)
    {
        mViewRight02.setBackgroundResource(resId);
    }

    /**
     * 设置右边第二个区域点击事件
     * R.id.fl_comactionbar_right02
     */
    public void setRightClickListener02(OnClickListener listener)
    {
        mViewRight02.setOnClickListener(listener);
    }
}
