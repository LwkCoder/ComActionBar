ComActionBar：通用的自定义ActionBar
====
自定义ActionBar，轻量级的自定义控件
------
<br/>
<br/>
###使用方法:<br/>
1.添加Gradle依赖：
```
dependencies{
         compile 'com.lwkandroid.widget:ComActionBar:1.0.2
    }
```
<br/>
2.xml中可自定义的属性【代码中也有对应的方法】：
```
<com.lwkandroid.widget.ComActionBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:bg_color="@android:color/holo_orange_dark" //背景色，默认为colorPrimary
        app:is_left_as_back="true" //设置左侧功能为“返回”,默认为false
        app:left_image="@drawable/cab_left" //设置左侧图片
        app:left_text="good" //设置左侧文案
        app:left_text_color="@android:color/holo_purple" //设置左侧文案文字颜色
        app:title_text="Title" //设置标题文案
        app:title_text_color="@android:color/holo_red_dark" //设置标题文案文字颜色
        app:right_image01="@drawable/cab_right01" //设置右侧第一张图片
        app:right_image02="@drawable/cab_right02" //设置右侧第二张图片
        app:right_text01="HEHE" //设置右侧第一个文案
        app:right_text02="HAHA" //设置右侧第二个文案
        app:right_text_color01="@android:color/holo_green_dark" //设置右侧第一个文案文字颜色
        app:right_text_color02="@android:color/holo_green_dark" //设置右侧第二个文案文字颜色
        />
```
<br/>
###混淆配置：<br/>
```
-dontwarn com.lwkandroid.widget.**
-keep class com.lwkandroid.widget.**{*;}
```
<br/>
###效果图:<br/>
![](https://github.com/Vanish136/ComActionBar/raw/master/screenshoot/cab_sample.jpg)