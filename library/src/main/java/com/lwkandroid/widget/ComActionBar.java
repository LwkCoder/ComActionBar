package com.lwkandroid.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * @description: ActionBar通用版
 * @author:
 * @date: 2021/5/10 10:17
 */
public class ComActionBar extends FrameLayout
{
    private TextView mTvLeft, mTvTitle, mTvRight01, mTvRight02;
    private View mViewDivider;

    private boolean mIsRippleEffect;
    private int mPressedColor;
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftIconWidth;
    private int mLeftIconHeight;
    private Drawable mLeftIconDrawable;
    private boolean mIsLeftClickToFinish;
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private String mRightText01;
    private int mRightTextColor01;
    private int mRightTextSize01;
    private int mRightIconWidth01;
    private int mRightIconHeight01;
    private Drawable mRightIconDrawable01;
    private String mRightText02;
    private int mRightTextColor02;
    private int mRightTextSize02;
    private int mRightIconWidth02;
    private int mRightIconHeight02;
    private Drawable mRightIconDrawable02;
    private boolean mIsShowDividerLine;
    private int mDividerLineColor;
    private int mDividerLineHeight;
    private int mChildVerticalPadding;
    private int mChildHorizontalPadding;
    private int mDrawablePadding;

    private OnItemClickListener mLeftOnItemClickListener;
    private OnItemClickListener mRightOnItemClickListener01;
    private OnItemClickListener mRightOnItemClickListener02;

    public ComActionBar(@NonNull Context context)
    {
        this(context, null);
    }

    public ComActionBar(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ComActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        inflate(context, R.layout.layout_action_bar_content, this);
        mTvLeft = findViewById(R.id.tv_actionbar_left);
        mTvTitle = findViewById(R.id.tv_actionbar_title);
        mTvRight01 = findViewById(R.id.tv_actionbar_right01);
        mTvRight02 = findViewById(R.id.tv_actionbar_right02);
        mViewDivider = findViewById(R.id.view_actionbar_divider);

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ComActionBar2);
        mIsRippleEffect = ta.getBoolean(R.styleable.ComActionBar2_ripple_effect, true);
        mPressedColor = ta.getColor(R.styleable.ComActionBar2_pressed_color,
                Utils.getColorResources(context, R.color.cab_pressed_color_default));
        mLeftText = ta.getString(R.styleable.ComActionBar2_left_text);
        mLeftTextColor = ta.getColor(R.styleable.ComActionBar2_left_text_color,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mLeftTextSize = ta.getDimensionPixelSize(R.styleable.ComActionBar2_left_text_size,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mLeftIconWidth = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_left_icon_width, -1);
        mLeftIconHeight = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_left_icon_height, -1);
        mLeftIconDrawable = ta.getDrawable(R.styleable.ComActionBar2_left_icon_drawable);
        mIsLeftClickToFinish = ta.getBoolean(R.styleable.ComActionBar2_left_click_to_finish, false);
        mTitleText = ta.getString(R.styleable.ComActionBar2_title_text);
        mTitleTextColor = ta.getColor(R.styleable.ComActionBar2_title_text_color,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mTitleTextSize = ta.getDimensionPixelSize(R.styleable.ComActionBar2_title_text_size,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_title_text_size_default));
        mRightText01 = ta.getString(R.styleable.ComActionBar2_right_text01);
        mRightTextColor01 = ta.getColor(R.styleable.ComActionBar2_right_text_color01,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mRightTextSize01 = ta.getDimensionPixelSize(R.styleable.ComActionBar2_right_text_size01,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mRightIconWidth01 = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_right_icon_width01, -1);
        mRightIconHeight01 = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_right_icon_height01, -1);
        mRightIconDrawable01 = ta.getDrawable(R.styleable.ComActionBar2_right_icon_drawable01);
        mRightText02 = ta.getString(R.styleable.ComActionBar2_right_text02);
        mRightTextColor02 = ta.getColor(R.styleable.ComActionBar2_right_text_color02,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mRightTextSize02 = ta.getDimensionPixelSize(R.styleable.ComActionBar2_right_text_size02,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mRightIconWidth02 = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_right_icon_width02, -1);
        mRightIconHeight02 = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_right_icon_height02, -1);
        mRightIconDrawable02 = ta.getDrawable(R.styleable.ComActionBar2_right_icon_drawable02);
        mIsShowDividerLine = ta.getBoolean(R.styleable.ComActionBar2_show_divider_line, true);
        mDividerLineColor = ta.getColor(R.styleable.ComActionBar2_divider_line_color,
                Utils.getColorResources(context, R.color.cab_divider_line_default));
        mDividerLineHeight = ta.getDimensionPixelSize(R.styleable.ComActionBar2_divider_line_height,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_divider_line_height_default));
        mChildVerticalPadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_child_vertical_padding,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_child_padding_vertical_default));
        mChildHorizontalPadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_child_horizontal_padding,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_child_padding_horizontal_default));
        mDrawablePadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar2_android_drawablePadding, 0);


        //初始化设置
        setLeftText(mLeftText);
        setLeftTextColor(mLeftTextColor);
        setLeftTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        updateLeftIcon();
        setIsLeftClickToFinish(mIsLeftClickToFinish);

        mTvTitle.setSelected(true);
        setTitleText(mTitleText);
        setTitleTextColor(mTitleTextColor);
        setTitleTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);

        setRightText01(mRightText01);
        setRightTextColor01(mRightTextColor01);
        setRightTextSize01(TypedValue.COMPLEX_UNIT_PX, mRightTextSize01);
        updateRightIcon01();

        setRightText02(mRightText02);
        setRightTextColor02(mRightTextColor02);
        setRightTextSize02(TypedValue.COMPLEX_UNIT_PX, mRightTextSize02);
        updateRightIcon02();

        setDividerLineColor(mDividerLineColor);
        setDividerLineHeight(TypedValue.COMPLEX_UNIT_PX, mDividerLineHeight);
        setIsShowDividerLine(mIsShowDividerLine);

        updateRippleEffect();
        updateChildPadding();
        updateDrawablePadding();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int leftMeasuredWidth = mTvLeft.getMeasuredWidth();
        int rightMeasuredWidth01 = mTvRight01.getMeasuredWidth();
        int rightMeasuredWidth02 = mTvRight02.getMeasuredWidth();

        //设置各TextView高度一致
        ViewGroup.LayoutParams layoutParams1 = mTvLeft.getLayoutParams();
        layoutParams1.height = measuredHeight;
        mTvLeft.setLayoutParams(layoutParams1);
        ViewGroup.LayoutParams layoutParams2 = mTvTitle.getLayoutParams();
        layoutParams2.height = measuredHeight;
        mTvTitle.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = mTvRight01.getLayoutParams();
        layoutParams3.height = measuredHeight;
        mTvRight01.setLayoutParams(layoutParams3);
        ViewGroup.LayoutParams layoutParams4 = mTvRight02.getLayoutParams();
        layoutParams4.height = measuredHeight;
        mTvRight02.setLayoutParams(layoutParams4);
        //设置标题的Margin
        MarginLayoutParams titleLayoutParams = (MarginLayoutParams) mTvTitle.getLayoutParams();
        titleLayoutParams.leftMargin = leftMeasuredWidth;
        titleLayoutParams.rightMargin = rightMeasuredWidth01 + rightMeasuredWidth02;
        mTvTitle.setLayoutParams(titleLayoutParams);
    }

    public void setRippleEffect(boolean enable)
    {
        this.mIsRippleEffect = enable;
        updateRippleEffect();
    }

    public void setPressedColorResId(@ColorRes int resId)
    {
        setPressedColor(Utils.getColorResources(getContext(), resId));
        updateRippleEffect();
    }

    public void setPressedColor(@ColorInt int color)
    {
        this.mPressedColor = color;
        updateRippleEffect();
    }

    public void setLeftText(@StringRes int resId)
    {
        setLeftText(getContext().getString(resId));
    }

    public void setLeftText(String text)
    {
        this.mLeftText = text;
        mTvLeft.setText(mLeftText);
    }

    public void setLeftTextColorResId(@ColorRes int resId)
    {
        setLeftTextColor(Utils.getColorResources(getContext(), resId));
    }

    public void setLeftTextColor(@ColorInt int color)
    {
        this.mLeftTextColor = color;
        mTvLeft.setTextColor(mLeftTextColor);
    }

    public void setLeftTextSize(int unit, int size)
    {
        this.mLeftTextSize = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        mTvLeft.setTextSize(unit, mLeftTextSize);
    }

    public void setLeftIconWidth(int unit, int size)
    {
        this.mLeftIconWidth = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateLeftIcon();
    }

    public void setLeftIconHeight(int unit, int size)
    {
        this.mLeftIconHeight = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateLeftIcon();
    }

    public void setLeftIconDrawable(@DrawableRes int resId)
    {
        setLeftIconDrawable(Utils.getDrawableResources(getContext(), resId));
    }

    public void setLeftIconDrawable(Drawable drawable)
    {
        this.mLeftIconDrawable = drawable;
        updateLeftIcon();
    }

    public void setIsLeftClickToFinish(boolean enable)
    {
        this.mIsLeftClickToFinish = enable;
        if (mIsLeftClickToFinish && getContext() instanceof Activity)
        {
            mTvLeft.setOnClickListener(v -> ((Activity) getContext()).finish());
        }
    }

    public void setTitleText(@StringRes int resId)
    {
        setTitleText(getResources().getString(resId));
    }

    public void setTitleText(String text)
    {
        this.mTitleText = text;
        mTvTitle.setText(mTitleText);
    }

    public void setTitleTextColorResId(@ColorRes int resId)
    {
        setTitleTextColor(Utils.getColorResources(getContext(), resId));
    }

    public void setTitleTextColor(@ColorInt int color)
    {
        this.mTitleTextColor = color;
        mTvTitle.setTextColor(mTitleTextColor);
    }

    public void setTitleTextSize(int unit, int size)
    {
        this.mTitleTextSize = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        mTvTitle.setTextSize(unit, mTitleTextSize);
    }

    public void setRightText01(@StringRes int resId)
    {
        setRightText01(getResources().getString(resId));
    }

    public void setRightText01(String text)
    {
        this.mRightText01 = text;
        mTvRight01.setText(mRightText01);
    }

    public void setRightTextColorResId01(@ColorRes int resId)
    {
        setRightTextColor01(Utils.getColorResources(getContext(), resId));
    }

    public void setRightTextColor01(@ColorInt int color)
    {
        this.mRightTextColor01 = color;
        mTvRight01.setTextColor(mRightTextColor01);
    }

    public void setRightTextSize01(int unit, int size)
    {
        this.mRightTextSize01 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        mTvRight01.setTextSize(unit, mRightTextSize01);
    }

    public void setRightIconWidth01(int unit, int size)
    {
        this.mRightIconWidth01 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateRightIcon01();
    }

    public void setRightIconHeight01(int unit, int size)
    {
        this.mRightIconHeight01 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateRightIcon01();
    }

    public void setRightIconDrawable01(@DrawableRes int resId)
    {
        setRightIconDrawable01(Utils.getDrawableResources(getContext(), resId));
        updateRightIcon01();
    }

    public void setRightIconDrawable01(Drawable drawable)
    {
        this.mRightIconDrawable01 = drawable;
        updateRightIcon01();
    }

    public void setRightText02(@StringRes int resId)
    {
        setRightText02(getResources().getString(resId));
    }

    public void setRightText02(String text)
    {
        this.mRightText02 = text;
        mTvRight02.setText(mRightText02);
    }

    public void setRightTextColorResId02(@ColorRes int resId)
    {
        setRightTextColor02(Utils.getColorResources(getContext(), resId));
    }

    public void setRightTextColor02(@ColorInt int color)
    {
        this.mRightTextColor02 = color;
        mTvRight02.setTextColor(mRightTextColor02);
    }

    public void setRightTextSize02(int unit, int size)
    {
        this.mRightTextSize02 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        mTvRight02.setTextSize(unit, mRightTextSize02);
    }

    public void setRightIconWidth02(int unit, int size)
    {
        this.mRightIconWidth02 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateRightIcon02();
    }

    public void setRightIconHeight02(int unit, int size)
    {
        this.mRightIconHeight02 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateRightIcon02();
    }

    public void setRightIconDrawable02(@DrawableRes int resId)
    {
        setRightIconDrawable02(Utils.getDrawableResources(getContext(), resId));
    }

    public void setRightIconDrawable02(Drawable drawable)
    {
        this.mRightIconDrawable02 = drawable;
        updateRightIcon02();
    }

    public void setIsShowDividerLine(boolean enable)
    {
        this.mIsShowDividerLine = enable;
        mViewDivider.setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
    }

    public void setDividerLineColorResId(@ColorRes int resId)
    {
        setDividerLineColor(Utils.getColorResources(getContext(), resId));
    }

    public void setDividerLineColor(@ColorInt int color)
    {
        this.mDividerLineColor = color;
        mViewDivider.setBackgroundColor(mDividerLineColor);
    }

    public void setDividerLineHeight(int unit, int size)
    {
        this.mDividerLineHeight = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = mViewDivider.getLayoutParams();
        layoutParams.height = mDividerLineHeight;
        mViewDivider.setLayoutParams(layoutParams);
    }

    public void setChildVerticalPadding(int unit, int size)
    {
        this.mChildVerticalPadding = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateChildPadding();
    }

    public void setChildHorizontalPadding(int unit, int size)
    {
        this.mChildHorizontalPadding = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateChildPadding();
    }

    public void setDrawablePadding(int unit, int size)
    {
        this.mDrawablePadding = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        updateDrawablePadding();
    }

    public void setLeftOnItemClickListener(OnItemClickListener listener)
    {
        this.mLeftOnItemClickListener = listener;
        if (mIsLeftClickToFinish)
        {
            Log.e("ComActionBar", "Unable to invoke the function setLeftOnItemClickListener() after isLeftClickToFinish() enabled!");
        } else
        {
            mTvLeft.setOnClickListener(v -> {
                if (mLeftOnItemClickListener != null)
                {
                    mLeftOnItemClickListener.onActionBarItemClicked(mTvLeft.getId(), mTvLeft, mViewDivider);
                }
            });
        }
    }

    public void setRightOnItemClickListener01(OnItemClickListener listener)
    {
        this.mRightOnItemClickListener01 = listener;
        mTvRight01.setOnClickListener(v -> {
            if (mRightOnItemClickListener01 != null)
            {
                mRightOnItemClickListener01.onActionBarItemClicked(mTvRight01.getId(), mTvRight01, mViewDivider);
            }
        });
    }

    public void setRightOnItemClickListener02(OnItemClickListener listener)
    {
        this.mRightOnItemClickListener02 = listener;
        mTvRight02.setOnClickListener(v -> {
            if (mRightOnItemClickListener02 != null)
            {
                mRightOnItemClickListener02.onActionBarItemClicked(mTvRight02.getId(), mTvRight02, mViewDivider);
            }
        });
    }

    private void updateRippleEffect()
    {
        if (mIsRippleEffect)
        {
            Utils.setViewBackground(mTvLeft, Utils.getRippleDrawable(getContext()));
            Utils.setViewBackground(mTvTitle, Utils.getRippleDrawable(getContext()));
            Utils.setViewBackground(mTvRight01, Utils.getRippleDrawable(getContext()));
            Utils.setViewBackground(mTvRight02, Utils.getRippleDrawable(getContext()));
        } else
        {
            Utils.setViewBackground(mTvLeft, new SelectorDrawable.Builder()
                    .setPressed(new ColorDrawable(mPressedColor))
                    .build());
            Utils.setViewBackground(mTvTitle, new SelectorDrawable.Builder()
                    .setPressed(new ColorDrawable(mPressedColor))
                    .build());
            Utils.setViewBackground(mTvRight01, new SelectorDrawable.Builder()
                    .setPressed(new ColorDrawable(mPressedColor))
                    .build());
            Utils.setViewBackground(mTvRight02, new SelectorDrawable.Builder()
                    .setPressed(new ColorDrawable(mPressedColor))
                    .build());
        }
    }

    private void updateLeftIcon()
    {
        if (mLeftIconDrawable == null)
        {
            mTvLeft.setCompoundDrawables(null, null, null, null);
        } else
        {
            if (mLeftIconWidth != -1 && mLeftIconHeight != -1)
            {
                mLeftIconDrawable.setBounds(0, 0, mLeftIconWidth, mLeftIconHeight);
            } else
            {
                mLeftIconDrawable.setBounds(0, 0, mLeftIconDrawable.getIntrinsicWidth(), mLeftIconDrawable.getIntrinsicHeight());
            }
            mTvLeft.setCompoundDrawables(mLeftIconDrawable, null, null, null);
        }
    }

    private void updateRightIcon01()
    {
        if (mRightIconDrawable01 == null)
        {
            mTvRight01.setCompoundDrawables(null, null, null, null);
        } else
        {
            if (mRightIconWidth01 != -1 && mRightIconHeight01 != -1)
            {
                mRightIconDrawable01.setBounds(0, 0, mRightIconWidth01, mRightIconHeight01);
            } else
            {
                mRightIconDrawable01.setBounds(0, 0, mRightIconDrawable01.getIntrinsicWidth(), mRightIconDrawable01.getIntrinsicHeight());
            }
            mTvRight01.setCompoundDrawables(mRightIconDrawable01, null, null, null);
        }
    }

    private void updateRightIcon02()
    {
        if (mRightIconDrawable02 == null)
        {
            mTvRight02.setCompoundDrawables(null, null, null, null);
        } else
        {
            if (mRightIconWidth02 != -1 && mRightIconHeight02 != -1)
            {
                mRightIconDrawable02.setBounds(0, 0, mRightIconWidth02, mRightIconHeight02);
            } else
            {
                mRightIconDrawable02.setBounds(0, 0, mRightIconDrawable02.getIntrinsicWidth(), mRightIconDrawable02.getIntrinsicHeight());
            }
            mTvRight02.setCompoundDrawables(mRightIconDrawable02, null, null, null);
        }
    }

    private void updateChildPadding()
    {
        mTvLeft.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
        mTvTitle.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
        mTvRight01.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
        mTvRight02.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
    }

    private void updateDrawablePadding()
    {
        mTvLeft.setCompoundDrawablePadding(mDrawablePadding);
        mTvRight01.setCompoundDrawablePadding(mDrawablePadding);
        mTvRight02.setCompoundDrawablePadding(mDrawablePadding);
    }


    public interface OnItemClickListener
    {
        void onActionBarItemClicked(int viewId, TextView textView, View dividerLine);
    }
}
