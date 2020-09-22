概述
        
android开发中，我们常常使用xml来写布局文件，这种实现方式不仅简单，而且表达能力更强。但是google提供的布局属性有限，有些功能的实现我们不得不使用代码，或者自定义控件的方式来实现。那有没有一种方法，可以将属性增强来实现额外的功能呢？例如我们常常使用background 来表示和设置背景，那是不是可以使用layout_radius来表示和设置圆角呢？

 

使用示例
需要在项目build.gradle中引用依赖

 implementation 'com.zhangzheng.superxml:library:1.1.0'
 另外在Application注册一行代码

  SuperXml.init(this)
  OVER

 

能力说明
 
属性增强
   圆角：         
   <View
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="20dp"
        android:background="#FF0000"
        app:layout_radius="40dp" />
  说明

       app:layout_radius 支持将控件背景设置为圆角，背景支持纯色背景或者图片，另外对于ImageView 的src如果想设置成圆角需要使用app:layout_src_radius，例如 

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    android:src="@mipmap/order_ic_shipper_default"
    app:layout_src_radius="10dp" />
 

   复合属性
       

  <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="asdasdasdasd"
        app:layout_textColor_pressedFalse="#00FF00"
        app:layout_textColor_pressedTrue="#FF0000" />
   说明

        一般情况下，我们要表示点击和普通状态下不同的字体颜色，或者背景会使用selector来定义一个文件，然后在布局文件中引用，一方面这样的使用很麻烦，另外一方面可读性也会降低（用户需要进入selector文件进行分析，才知道代码表达意图）。这边封装了常用的复用属性，如下：

属性	属性类型	说明
layout_background_enableTrue
layout_background_enableFalse
reference|color（资源或者颜色）
背景（是否可用）
layout_background_pressedTrue
layout_background_pressedFalse
reference|color（资源或者颜色）	背景（是否按压）
layout_background_selectedTrue
layout_background_selectedTrue
reference|color（资源或者颜色）	背景（是否选择）
layout_textColor_enableTrue
layout_textColor_enableFalse
reference|color（资源或者颜色）	字体颜色（是否可用）
layout_textColor_pressedTrue
layout_textColor_pressedFalse
 

reference|color（资源或者颜色）	字体颜色（是否按压）
layout_textColor_selectedTrue
layout_textColor_selectedFalse
reference|color（资源或者颜色）	字体颜色（是否选择）
 	 	 
 
边框   
 <View
        android:id="@+id/view"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="20dp"
        android:background="#FF0000"
        app:layout_border_color="#0000FF"
        app:layout_border_width="1dp"
        app:layout_radius="40dp" />
 

说明

      比较简单，layout_border_color表示边框颜色，layout_border_width表示边框粗细，和radius一起使用表示边框圆角。

 

 

虚线
 <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_marginTop="20dp"
        android:background="#FF0000"
        app:layout_dash_gap="10dp"
        app:layout_dash_height="1dp"
        app:layout_dash_width="5dp" />
 

说明

     可以在任何视图上使用（建议在View中定义），必须同时定义grap、dash_height、dash_width。支持横虚线，和竖虚线，这里会检测视图宽高来确定。属性说明：dash_grap(虚线间距)、dash_width(单个小线的宽)、dash_height(单个小线的高)

 

 

视图替换或增强
          这个能力可能会将布局文件中的视图替换成其他控件、或者对其进行增强。

 

滚动视图
      为了适配小屏手机，我们可能会在每一个布局文件中加上一层ScrollView，现在对容器控件进行能力增强。

 <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="130dp"
        android:gravity="center"
        android:orientation="vertical"
        android:scrollbars="none"
        app:layout_canScroll="true">
说明

     在需要滚动的视图上添加属性app:layout_canScroll="true"，来使其获得滚动的能力。另外所有scrollView的属性，可以配置在该容器控件中。

 

 

属性覆盖
          有一种很常见的业务场景，在一个条目中有多个控件，控件大多数属性是相同的（例如TextView的字体颜色、大小等），一般我们会给每一个控件加上相同的属性（冗余）、或者定义公共样式（太麻烦）。现在参考html的布局方式，在父控件中设置公共样式，给子控件当默认值。

  <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:textColor="#FF0000"
        android:textSize="20sp"
        android:textStyle="italic"
        android:scaleType="center"
        app:layout_cover_children="true">
 
        <ImageView
 
            android:layout_width="50dp"
            android:src="@mipmap/ic_launcher"
            android:layout_height="50dp"/>
 
        <TextView
            android:capitalize="none"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="#FF00FF"
            android:textStyle="italic"
            android:text="11111" />
 
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:textSize="13dp"
            android:text="22222" />
 
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:textSize="14dp"
            android:text="333333" />
 
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:textSize="15dp"
            android:text="444444" />
 
    </LinearLayout>
 

支持的属性

       

控件类型	属性	 
TextView	
textColor
 
textSize
 
text
 
maxLines
 
maxEms
 
textColorHint
 
hint
 
textDirection
 
textStyle
 
capitalize
 
ImageView	
src
 
scaleType
 
 

扩展
 

属性扩展
  SuperXml.addDecorate(object : IDecorateView() {
            override fun initExtraInfo(typedArray: TypedArray): Boolean {
            }
 
            override fun decorate(view: View) {
            }
        })
实现参考

internal class RadiusDecorate(var radius: Float = 0f) : IDecorateView() {
 
    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        radius = typedArray.getDimension(R.styleable.decorate_view_layout_radius,0f)
        return radius > 0
    }
 
    override fun decorate(view: View)= view.setRadius(radius)
 
}
 

控件替换 OR 增强
 SuperXml.addDecorate(object :IWrapDecorateView(){
            override fun decorateView(view: View): View {
            }
 
            override fun initExtraInfo(typedArray: TypedArray): Boolean {
            }
        })
实现参考

internal class ScrollWrapDecorate(var canScroll: Boolean = false) : IWrapDecorateView() {
 
    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        canScroll = typedArray.getBoolean(R.styleable.decorate_view_layout_canScroll, false)
        return canScroll
    }
 
    override fun decorateView(view: View): View {
        return ScrollViewProxy(
            view,
            attributeSet
        )
    }
 
 
}
 
属性覆盖
  SuperXml.addCoverAttributeParse(object : AbsChildViewParse<TextView>(){
            override fun createInfoView(context: Context, attributeSet: AttributeSet?): TextView {
            }
 
            override fun coverAttribute(): MutableList<*> {
            }
        })
实现参考

class TextViewCoverParse : AbsChildViewParse<TextView>() {
 
    override fun createInfoView(context: Context, attributeSet: AttributeSet?): TextView =
        TextView(context, attributeSet)
 
    override fun coverAttribute(): MutableList<*> = mutableListOf(
        AttributeInfo("textSize",{ textSize }) { value -> textSize = value },
        AttributeInfo("textColor",{ textColors }) { value -> setTextColor(value) },
        AttributeInfo("text",{ text }) { text -> setText(text) },
        AttributeInfo("maxLines",{ maxLines }) { maxLines -> setMaxLines(maxLines) },
        AttributeInfo("maxEms",{ maxEms }) { maxEms -> setMaxEms(maxEms) },
        AttributeInfo("textColorHint",{ hintTextColors }) { hintTextColors -> setHintTextColor(hintTextColors) },
        AttributeInfo("hint",{ hint }) { hint -> setHint(hint) },
        AttributeInfo("textDirection",{ textDirection }) { textDirection -> setTextDirection(textDirection) },
        AttributeInfo("textStyle",{ typeface }) { typeface -> setTypeface(typeface) },
        AttributeInfo("capitalize",{ inputType }) { inputType -> setInputType(inputType) }
    )
 
 
 
}
 

代码：github
https://github.com/long8313002/SuperXml
