package com.lwkandroid.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * @description: 子控件创建类
 * @author:
 * @date: 2021/5/11 15:21
 */
interface IComActionBarChildCreation
{
    TextView createLeftTextView(Context context);

    TextView createTitleTextView(Context context);

    TextView createRightTextView01(Context context);

    TextView createRightTextView02(Context context);

    View createDividerLineView(Context context);
}
