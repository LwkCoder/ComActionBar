ComActionBar：通用的自定义ActionBar
====
自定义ActionBar，轻量级的自定义控件
------

### 使用方法:

**1.添加Gradle依赖：**
【最新版本号以[这里](https://github.com/Vanish136/ComActionBar/releases)为准】

```
    #last-version请查看上面的最新版本号

    #AndroidStudio3.0以下
    compile 'com.lwkandroid.widget:ComActionBar:last-version'

    #AndroidStudio3.0以上
    implemetation 'com.lwkandroid.widget:ComActionBar:last-version'
```
<br/>

**2.xml中可自定义的属性【代码中也有对应的方法】：**

```
<com.lwkandroid.widget.comactionbar.ComActionBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:bg_color="@android:color/holo_orange_dark" //背景色，默认为colorPrimary
        app:is_left_as_back="true" //设置左侧功能为“返回”,默认为false
        app:is_left_as_back_without_text="true" //设置左侧功能为“返回”，不带文案，默认为false
        app:left_back_drawable="@drawable/ic_back" //设置“返回”箭头
        app:left_image="@drawable/cab_left" //设置左侧图片
        app:all_text_color="@android:color/holo_red_light" //设置全局文字颜色，默认白色
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
        app:text_size_item="14sp" //设置除标题外文字大小
        app:text_size_title="22sp" //设置标题文字大小
        />
```
<br/>

### 混淆配置：
```
-dontwarn com.lwk.comactionbar.**
-keep class com.lwk.comactionbar.**{*;}
```
<br/>

### 效果图:
![](https://github.com/Vanish136/ComActionBar/raw/master/screenshoot/cab_sample.jpg)