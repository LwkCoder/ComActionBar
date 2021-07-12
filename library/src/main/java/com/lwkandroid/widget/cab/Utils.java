package com.lwkandroid.widget.cab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

/**
 * @description:工具类
 * @author:
 * @date: 2021/5/10 15:16
 */
final class Utils
{
    public static Drawable getDrawableResources(Context context, @DrawableRes int id)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            return context.getResources().getDrawable(id, context.getTheme());
        } else
        {
            return context.getResources().getDrawable(id);
        }
    }

    public static int getColorResources(Context context, @ColorRes int id)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return context.getColor(id);
        } else
        {
            return context.getResources().getColor(id);
        }
    }


    public static void setViewBackground(View view, Drawable drawable)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            if (view != null)
                view.setBackground(drawable);
        } else
        {
            if (view != null)
                view.setBackgroundDrawable(drawable);
        }
    }

    public static Drawable getRippleDrawable(Context context)
    {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true))
        {
            return getDrawableResources(context, typedValue.resourceId);
        }
        return null;
    }
}
