ComActionBar：通用的自定义ActionBar
====
自定义ActionBar，轻量级的自定义控件
------

**2.0.0版本开始发布到MavenCentral，1.x版本不兼容**

### 使用方法:

**1.添加Gradle依赖：**
【最新版本号以[这里](https://github.com/Vanish136/ComActionBar/releases)为准】

```
    #last-version请查看上面的最新版本号

    #2.0.0以上引用方式
    implemetation 'com.lwkandroid.library:ComActionBar:last-version'
```
<br/>

**2.xml中可自定义的属性【代码中也有对应的方法】：**

```
    <declare-styleable name="ComActionBar2">
        //是否启用水波纹点击效果，默认true
        <attr name="ripple_effect" format="boolean" />

        //当水波纹效果为false时，设置点击后的按压色，默认#25000000
        <attr name="pressed_color" format="color" />

        //左侧文字内容
        <attr name="left_text" format="string" />

        //左侧文字颜色，默认#FFFFFF
        <attr name="left_text_color" format="color" />

        //左侧文字大小，默认12sp
        <attr name="left_text_size" format="dimension" />

        //左侧icon宽度，需要和left_icon_height同时设置才生效
        <attr name="left_icon_width" format="dimension" />

        //左侧icon高度，需要和left_icon_width同时设置才生效
        <attr name="left_icon_height" format="dimension" />

        //左侧icon图片
        <attr name="left_icon_drawable" format="reference" />

        //左侧icon方向，默认start，2.1.0版本新增
        <attr name="left_icon_direction">
            <enum name="start" value="0" />
            <enum name="top" value="1" />
            <enum name="end" value="2" />
            <enum name="bottom" value="3" />
        </attr>

        //左侧点击后是否为关闭Activity，默认false
        <attr name="left_click_to_finish" format="boolean" />

        //标题文字内容
        <attr name="title_text" format="string" />

        //标题文字颜色，默认#FFFFFF
        <attr name="title_text_color" format="color" />

        //标题文字大小，默认14sp
        <attr name="title_text_size" format="dimension" />

        //右侧第一个文字内容
        <attr name="right_text01" format="string" />

        //右侧第一个文字颜色，默认#FFFFFF
        <attr name="right_text_color01" format="color" />

        //右侧第一个文字大小，默认12sp
        <attr name="right_text_size01" format="dimension" />

        //右侧第一个icon宽度，需要和right_icon_height01同时设置才生效
        <attr name="right_icon_width01" format="dimension" />

        //右侧第一个icon高度，需要和right_icon_width同时设置才生效
        <attr name="right_icon_height01" format="dimension" />

        //右侧第一个icon图片
        <attr name="right_icon_drawable01" format="reference" />

        //右侧第一个icon方向，默认start，2.1.0版本新增
        <attr name="right_icon_direction01">
            <enum name="start" value="0" />
            <enum name="top" value="1" />
            <enum name="end" value="2" />
            <enum name="bottom" value="3" />
        </attr>

        //右侧第二个文字内容
        <attr name="right_text02" format="string" />

        //右侧第二个文字颜色，默认#FFFFFF
        <attr name="right_text_color02" format="color" />

        //右侧第二个文字大小，默认12sp
        <attr name="right_text_size02" format="dimension" />

        //右侧第二个icon宽度，需要和right_icon_height02同时设置才生效
        <attr name="right_icon_width02" format="dimension" />

        //右侧第二个icon高度，需要和right_icon_width02同时设置才生效
        <attr name="right_icon_height02" format="dimension" />

        //右侧第二个icon图片
        <attr name="right_icon_drawable02" format="reference" />

        //右侧第二个icon方向，默认start，2.1.0版本新增
        <attr name="right_icon_direction02">
            <enum name="start" value="0" />
            <enum name="top" value="1" />
            <enum name="end" value="2" />
            <enum name="bottom" value="3" />
        </attr>

        //是否显示底部分割线，默认true
        <attr name="show_divider_line" format="boolean" />

        //底部分割线颜色，默认#FCF4F4F6
        <attr name="divider_line_color" format="color" />

        //底部分割线高度，默认1px
        <attr name="divider_line_height" format="dimension" />

        //控件垂直方向内padding，默认14dp
        <attr name="child_vertical_padding" format="dimension" />

        //控件水平方向内padding，默认8dp
        <attr name="child_horizontal_padding" format="dimension" />

        //文字和icon的间距，默认0dp
        <attr name="android:drawablePadding" />
    </declare-styleable>
```
<br/>

### 混淆配置：
无需混淆
<br/>

### 效果图:
![](https://github.com/Vanish136/ComActionBar/raw/master/screenshoot/sample.jpg)