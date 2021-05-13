package com.lwkandroid.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * @description: ActionBar通用版
 * @author:
 * @date: 2021/5/10 10:17
 */
public class ComActionBar extends ViewGroup
{
    public static final int ICON_DIRECTION_START = 0;
    public static final int ICON_DIRECTION_TOP = 1;
    public static final int ICON_DIRECTION_END = 2;
    public static final int ICON_DIRECTION_BOTTOM = 3;

    @IntDef({ICON_DIRECTION_START, ICON_DIRECTION_TOP, ICON_DIRECTION_END, ICON_DIRECTION_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconDirection
    {

    }

    private TextView mTvLeft;
    private TextView mTvTitle;
    private TextView mTvRight01;
    private TextView mTvRight02;
    private View mViewDivider;

    private IComActionBarChildCreation mChildCreationImpl;

    private boolean mIsRippleEffect;
    private int mPressedColor;
    private String mLeftText;
    private int mLeftTextColor;
    private int mLeftTextSize;
    private int mLeftIconWidth;
    private int mLeftIconHeight;
    private Drawable mLeftIconDrawable;
    private int mLeftIconDirection;
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
    private int mRightIconDirection01;
    private String mRightText02;
    private int mRightTextColor02;
    private int mRightTextSize02;
    private int mRightIconWidth02;
    private int mRightIconHeight02;
    private Drawable mRightIconDrawable02;
    private int mRightIconDirection02;
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
        createChildViews(context);

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ComActionBar);
        mIsRippleEffect = ta.getBoolean(R.styleable.ComActionBar_ripple_effect, true);
        mPressedColor = ta.getColor(R.styleable.ComActionBar_pressed_color,
                Utils.getColorResources(context, R.color.cab_pressed_color_default));
        mLeftText = ta.getString(R.styleable.ComActionBar_left_text);
        mLeftTextColor = ta.getColor(R.styleable.ComActionBar_left_text_color,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mLeftTextSize = ta.getDimensionPixelSize(R.styleable.ComActionBar_left_text_size,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mLeftIconWidth = ta.getDimensionPixelOffset(R.styleable.ComActionBar_left_icon_width, -1);
        mLeftIconHeight = ta.getDimensionPixelOffset(R.styleable.ComActionBar_left_icon_height, -1);
        mLeftIconDrawable = ta.getDrawable(R.styleable.ComActionBar_left_icon_drawable);
        mLeftIconDirection = ta.getInt(R.styleable.ComActionBar_left_icon_direction, ICON_DIRECTION_START);
        mIsLeftClickToFinish = ta.getBoolean(R.styleable.ComActionBar_left_click_to_finish, false);
        mTitleText = ta.getString(R.styleable.ComActionBar_title_text);
        mTitleTextColor = ta.getColor(R.styleable.ComActionBar_title_text_color,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mTitleTextSize = ta.getDimensionPixelSize(R.styleable.ComActionBar_title_text_size,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_title_text_size_default));
        mRightText01 = ta.getString(R.styleable.ComActionBar_right_text01);
        mRightTextColor01 = ta.getColor(R.styleable.ComActionBar_right_text_color01,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mRightTextSize01 = ta.getDimensionPixelSize(R.styleable.ComActionBar_right_text_size01,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mRightIconWidth01 = ta.getDimensionPixelOffset(R.styleable.ComActionBar_right_icon_width01, -1);
        mRightIconHeight01 = ta.getDimensionPixelOffset(R.styleable.ComActionBar_right_icon_height01, -1);
        mRightIconDrawable01 = ta.getDrawable(R.styleable.ComActionBar_right_icon_drawable01);
        mRightIconDirection01 = ta.getInt(R.styleable.ComActionBar_right_icon_direction01, ICON_DIRECTION_START);
        mRightText02 = ta.getString(R.styleable.ComActionBar_right_text02);
        mRightTextColor02 = ta.getColor(R.styleable.ComActionBar_right_text_color02,
                Utils.getColorResources(context, R.color.cab_text_color_default));
        mRightTextSize02 = ta.getDimensionPixelSize(R.styleable.ComActionBar_right_text_size02,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_menu_text_size_default));
        mRightIconWidth02 = ta.getDimensionPixelOffset(R.styleable.ComActionBar_right_icon_width02, -1);
        mRightIconHeight02 = ta.getDimensionPixelOffset(R.styleable.ComActionBar_right_icon_height02, -1);
        mRightIconDrawable02 = ta.getDrawable(R.styleable.ComActionBar_right_icon_drawable02);
        mRightIconDirection02 = ta.getInt(R.styleable.ComActionBar_right_icon_direction02, ICON_DIRECTION_START);
        mIsShowDividerLine = ta.getBoolean(R.styleable.ComActionBar_show_divider_line, true);
        mDividerLineColor = ta.getColor(R.styleable.ComActionBar_divider_line_color,
                Utils.getColorResources(context, R.color.cab_divider_line_default));
        mDividerLineHeight = ta.getDimensionPixelSize(R.styleable.ComActionBar_divider_line_height,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_divider_line_height_default));
        mChildVerticalPadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar_child_vertical_padding,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_child_padding_vertical_default));
        mChildHorizontalPadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar_child_horizontal_padding,
                context.getResources().getDimensionPixelOffset(R.dimen.cab_child_padding_horizontal_default));
        mDrawablePadding = ta.getDimensionPixelOffset(R.styleable.ComActionBar_android_drawablePadding, 0);

        initUi();
    }

    private void createChildViews(Context context)
    {
        removeAllViews();

        if (mChildCreationImpl == null)
        {
            mChildCreationImpl = new DefaultComActionBarChildCreationImpl();
        }

        mTvLeft = mChildCreationImpl.createLeftTextView(context);
        mTvTitle = mChildCreationImpl.createTitleTextView(context);
        mTvRight01 = mChildCreationImpl.createRightTextView01(context);
        mTvRight02 = mChildCreationImpl.createRightTextView02(context);
        mViewDivider = mChildCreationImpl.createDividerLineView(context);

        addView(mTvLeft);
        addView(mTvTitle);
        addView(mTvRight01);
        addView(mTvRight02);
        addView(mViewDivider);
    }

    private void initUi()
    {
        //初始化设置
        setLeftText(mLeftText);
        setLeftTextColor(mLeftTextColor);
        setLeftTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        updateLeftIcon();
        setIsLeftClickToFinish(mIsLeftClickToFinish);

        if (mTvTitle != null)
        {
            mTvTitle.setSelected(true);
        }
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
        if (mTvLeft == null || mTvTitle == null || mTvRight01 == null || mTvRight02 == null)
        {
            return;
        }

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);

        mTvLeft.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int leftMeasuredWidth = mTvLeft.getMeasuredWidth();
        int leftMeasuredHeight = mTvLeft.getMeasuredHeight();

        mTvTitle.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int titleMeasuredHeight = mTvTitle.getMeasuredHeight();

        mTvRight01.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int rightMeasuredWidth01 = mTvRight01.getMeasuredWidth();
        int rightMeasuredHeight01 = mTvRight01.getMeasuredHeight();

        mTvRight02.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int rightMeasuredWidth02 = mTvRight02.getMeasuredWidth();
        int rightMeasuredHeight02 = mTvRight02.getMeasuredHeight();

        int maxChildHeight = Math.max(leftMeasuredHeight, titleMeasuredHeight);
        maxChildHeight = Math.max(maxChildHeight, rightMeasuredHeight01);
        maxChildHeight = Math.max(maxChildHeight, rightMeasuredHeight02);

        //        Log.e("ActionBar", "measureWidth=" + measureWidth + "\n" +
        //                "leftMeasuredWidth=" + leftMeasuredWidth + "\n"+
        //                "leftMeasuredHeight=" + leftMeasuredHeight + "\n"+
        //                "titleMeasuredHeight=" + titleMeasuredHeight + "\n"+
        //                "rightMeasuredWidth01=" + rightMeasuredWidth01 + "\n"+
        //                "rightMeasuredHeight01=" + rightMeasuredHeight01 + "\n"+
        //                "rightMeasuredWidth02=" + rightMeasuredWidth02 + "\n"+
        //                "rightMeasuredHeight02=" + rightMeasuredHeight02 + "\n"+
        //                "maxChildHeight=" + maxChildHeight + "\n"
        //        );

        measureChildWithExactlyMode(mTvLeft, leftMeasuredWidth, maxChildHeight);
        measureChildWithExactlyMode(mTvRight01, rightMeasuredWidth01, maxChildHeight);
        measureChildWithExactlyMode(mTvRight02, rightMeasuredWidth02, maxChildHeight);
        measureChildWithExactlyMode(mTvTitle, measureWidth - 2 * Math.max(leftMeasuredWidth, rightMeasuredWidth01 + rightMeasuredWidth02), maxChildHeight);
        measureChildWithExactlyMode(mViewDivider, measureWidth, mDividerLineHeight);

        setMeasuredDimension(measureWidth, maxChildHeight + mDividerLineHeight);
    }

    private void measureChildWithExactlyMode(View childView, int width, int height)
    {
        int childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        childView.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int totalWidth = getMeasuredWidth();
        int titleLeftStart = Math.max(mTvLeft.getMeasuredWidth(), mTvRight01.getMeasuredWidth() + mTvRight02.getMeasuredWidth());
        int rightLeftStart01 = totalWidth - mTvRight01.getMeasuredWidth();
        int rightLeftStart02 = totalWidth - mTvRight02.getMeasuredWidth() - mTvRight01.getMeasuredWidth();

        mTvLeft.layout(0, 0, mTvLeft.getMeasuredWidth(), mTvLeft.getMeasuredHeight());
        mTvTitle.layout(titleLeftStart, 0, titleLeftStart + mTvTitle.getMeasuredWidth(), mTvTitle.getMeasuredHeight());
        mTvRight01.layout(rightLeftStart01, 0, rightLeftStart01 + mTvRight01.getMeasuredWidth(), mTvRight01.getMeasuredHeight());
        mTvRight02.layout(rightLeftStart02, 0, rightLeftStart02 + mTvRight02.getMeasuredWidth(), mTvRight02.getMeasuredHeight());
        mViewDivider.layout(0, mTvLeft.getMeasuredHeight(), totalWidth, mTvLeft.getMeasuredHeight() + mViewDivider.getMeasuredHeight());
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
        if (mTvLeft != null)
        {
            mTvLeft.setText(mLeftText);
        }
    }

    public void setLeftTextColorResId(@ColorRes int resId)
    {
        setLeftTextColor(Utils.getColorResources(getContext(), resId));
    }

    public void setLeftTextColor(@ColorInt int color)
    {
        this.mLeftTextColor = color;
        if (mTvLeft != null)
        {
            mTvLeft.setTextColor(mLeftTextColor);
        }
    }

    public void setLeftTextSize(int unit, int size)
    {
        this.mLeftTextSize = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        if (mTvLeft != null)
        {
            mTvLeft.setTextSize(unit, mLeftTextSize);
        }
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                if (mTvLeft != null)
                {
                    mTvLeft.setOnClickListener(v -> ((Activity) getContext()).finishAfterTransition());
                }
            } else
            {
                if (mTvLeft != null)
                {
                    mTvLeft.setOnClickListener(v -> ((Activity) getContext()).finish());
                }
            }
        }
    }

    public void setTitleText(@StringRes int resId)
    {
        setTitleText(getResources().getString(resId));
    }

    public void setTitleText(String text)
    {
        this.mTitleText = text;
        if (mTvTitle != null)
        {
            mTvTitle.setText(mTitleText);
        }
    }

    public void setTitleTextColorResId(@ColorRes int resId)
    {
        setTitleTextColor(Utils.getColorResources(getContext(), resId));
    }

    public void setTitleTextColor(@ColorInt int color)
    {
        this.mTitleTextColor = color;
        if (mTvTitle != null)
        {
            mTvTitle.setTextColor(mTitleTextColor);
        }
    }

    public void setTitleTextSize(int unit, int size)
    {
        this.mTitleTextSize = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        if (mTvTitle != null)
        {
            mTvTitle.setTextSize(unit, mTitleTextSize);
        }
    }

    public void setRightText01(@StringRes int resId)
    {
        setRightText01(getResources().getString(resId));
    }

    public void setRightText01(String text)
    {
        this.mRightText01 = text;
        if (mTvRight01 != null)
        {
            mTvRight01.setText(mRightText01);
        }
    }

    public void setRightTextColorResId01(@ColorRes int resId)
    {
        setRightTextColor01(Utils.getColorResources(getContext(), resId));
    }

    public void setRightTextColor01(@ColorInt int color)
    {
        this.mRightTextColor01 = color;
        if (mTvRight01 != null)
        {
            mTvRight01.setTextColor(mRightTextColor01);
        }
    }

    public void setRightTextSize01(int unit, int size)
    {
        this.mRightTextSize01 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        if (mTvRight01 != null)
        {
            mTvRight01.setTextSize(unit, mRightTextSize01);
        }
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
        if (mTvRight02 != null)
        {
            mTvRight02.setText(mRightText02);
        }
    }

    public void setRightTextColorResId02(@ColorRes int resId)
    {
        setRightTextColor02(Utils.getColorResources(getContext(), resId));
    }

    public void setRightTextColor02(@ColorInt int color)
    {
        this.mRightTextColor02 = color;
        if (mTvRight02 != null)
        {
            mTvRight02.setTextColor(mRightTextColor02);
        }
    }

    public void setRightTextSize02(int unit, int size)
    {
        this.mRightTextSize02 = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        if (mTvRight02 != null)
        {
            mTvRight02.setTextSize(unit, mRightTextSize02);
        }
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
        if (mViewDivider != null)
        {
            mViewDivider.setVisibility(enable ? View.VISIBLE : View.GONE);
        }
    }

    public void setDividerLineColorResId(@ColorRes int resId)
    {
        setDividerLineColor(Utils.getColorResources(getContext(), resId));
    }

    public void setDividerLineColor(@ColorInt int color)
    {
        this.mDividerLineColor = color;
        if (mViewDivider != null)
        {
            mViewDivider.setBackgroundColor(mDividerLineColor);
        }
    }

    public void setDividerLineHeight(int unit, int size)
    {
        this.mDividerLineHeight = (int) TypedValue.applyDimension(unit, size, getResources().getDisplayMetrics());
        if (mViewDivider != null)
        {
            ViewGroup.LayoutParams layoutParams = mViewDivider.getLayoutParams();
            layoutParams.height = mDividerLineHeight;
            mViewDivider.setLayoutParams(layoutParams);
        }
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
            if (mTvLeft != null)
            {
                mTvLeft.setOnClickListener(v -> {
                    if (mLeftOnItemClickListener != null)
                    {
                        mLeftOnItemClickListener.onActionBarItemClicked(mTvLeft.getId(), mTvLeft, mViewDivider);
                    }
                });
            }
        }
    }

    public void setRightOnItemClickListener01(OnItemClickListener listener)
    {
        this.mRightOnItemClickListener01 = listener;
        if (mTvRight01 != null)
        {
            mTvRight01.setOnClickListener(v -> {
                if (mRightOnItemClickListener01 != null)
                {
                    mRightOnItemClickListener01.onActionBarItemClicked(mTvRight01.getId(), mTvRight01, mViewDivider);
                }
            });
        }
    }

    public void setRightOnItemClickListener02(OnItemClickListener listener)
    {
        this.mRightOnItemClickListener02 = listener;
        if (mTvRight02 != null)
        {
            mTvRight02.setOnClickListener(v -> {
                if (mRightOnItemClickListener02 != null)
                {
                    mRightOnItemClickListener02.onActionBarItemClicked(mTvRight02.getId(), mTvRight02, mViewDivider);
                }
            });
        }
    }

    public IComActionBarChildCreation getChildCreationImpl()
    {
        return mChildCreationImpl;
    }

    public void setChildCreationImpl(IComActionBarChildCreation childCreation)
    {
        this.mChildCreationImpl = childCreation;
        createChildViews(getContext());
        initUi();
        requestLayout();
    }

    public int getLeftIconDirection()
    {
        return mLeftIconDirection;
    }

    public void setLeftIconDirection(@IconDirection int direction)
    {
        this.mLeftIconDirection = direction;
    }

    public int getRightIconDirection01()
    {
        return mRightIconDirection01;
    }

    public void setRightIconDirection01(@IconDirection int direction)
    {
        this.mRightIconDirection01 = direction;
    }

    public int getRightIconDirection02()
    {
        return mRightIconDirection02;
    }

    public void setRightIconDirection02(@IconDirection int direction)
    {
        this.mRightIconDirection02 = direction;
    }

    public TextView getTvLeft()
    {
        return mTvLeft;
    }

    public TextView getTvTitle()
    {
        return mTvTitle;
    }

    public TextView getTvRight01()
    {
        return mTvRight01;
    }

    public TextView getTvRight02()
    {
        return mTvRight02;
    }

    public View getViewDivider()
    {
        return mViewDivider;
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
        if (mTvLeft == null)
        {
            return;
        }

        Drawable[] drawables = mTvLeft.getCompoundDrawables();
        if (mLeftIconDrawable != null)
        {
            if (mLeftIconWidth != -1 && mLeftIconHeight != -1)
            {
                mLeftIconDrawable.setBounds(0, 0, mLeftIconWidth, mLeftIconHeight);
            } else
            {
                mLeftIconDrawable.setBounds(0, 0, mLeftIconDrawable.getIntrinsicWidth(), mLeftIconDrawable.getIntrinsicHeight());
            }
        }
        drawables[mLeftIconDirection] = mLeftIconDrawable;
        mTvLeft.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    private void updateRightIcon01()
    {
        if (mTvRight01 == null)
        {
            return;
        }

        Drawable[] drawables = mTvRight01.getCompoundDrawables();
        if (mRightIconDrawable01 != null)
        {
            if (mRightIconWidth01 != -1 && mRightIconHeight01 != -1)
            {
                mRightIconDrawable01.setBounds(0, 0, mRightIconWidth01, mRightIconHeight01);
            } else
            {
                mRightIconDrawable01.setBounds(0, 0, mRightIconDrawable01.getIntrinsicWidth(), mRightIconDrawable01.getIntrinsicHeight());
            }
        }
        drawables[mRightIconDirection01] = mRightIconDrawable01;
        mTvRight01.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    private void updateRightIcon02()
    {
        if (mTvRight02 == null)
        {
            return;
        }

        Drawable[] drawables = mTvRight02.getCompoundDrawables();
        if (mRightIconDrawable02 != null)
        {
            if (mRightIconWidth02 != -1 && mRightIconHeight02 != -1)
            {
                mRightIconDrawable02.setBounds(0, 0, mRightIconWidth02, mRightIconHeight02);
            } else
            {
                mRightIconDrawable02.setBounds(0, 0, mRightIconDrawable02.getIntrinsicWidth(), mRightIconDrawable02.getIntrinsicHeight());
            }
        }
        drawables[mRightIconDirection01] = mRightIconDrawable02;
        mTvRight02.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    private void updateChildPadding()
    {
        if (!TextUtils.isEmpty(mLeftText) || mLeftIconDrawable != null)
        {
            if (mTvLeft != null)
            {
                mTvLeft.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
            }
        }
        if (!TextUtils.isEmpty(mTitleText))
        {
            if (mTvTitle != null)
            {
                mTvTitle.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
            }
        }
        if (!TextUtils.isEmpty(mRightText01) || mRightIconDrawable01 != null)
        {
            if (mTvRight01 != null)
            {
                mTvRight01.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
            }
        }
        if (!TextUtils.isEmpty(mRightText02) || mRightIconDrawable02 != null)
        {
            if (mTvRight02 != null)
            {
                mTvRight02.setPadding(mChildHorizontalPadding, mChildVerticalPadding, mChildHorizontalPadding, mChildVerticalPadding);
            }
        }
    }

    private void updateDrawablePadding()
    {
        if (mTvLeft != null)
        {
            mTvLeft.setCompoundDrawablePadding(mDrawablePadding);
        }
        if (mTvRight01 != null)
        {
            mTvRight01.setCompoundDrawablePadding(mDrawablePadding);
        }
        if (mTvRight02 != null)
        {
            mTvRight02.setCompoundDrawablePadding(mDrawablePadding);
        }
    }


    public interface OnItemClickListener
    {
        void onActionBarItemClicked(int viewId, TextView textView, View dividerLine);
    }
}
