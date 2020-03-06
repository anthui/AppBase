升级内容：
1、glide_v3.7升级 4.9（新版改用 kotline语言）---------------ImageLoadUtil（初步、未完成）
    glide新版的使用方式，api定制、缓存原则、图片转化、加载Gif、生命周期等等

2、网络框架升级 Retrofit+OkHttp+RxAndroid+MVP  ---------------------BaseHttpAction
    1、rxAndroid的实现原理、使用方法---------管理异步线程
        涉及思想：响应式编程，观察者模式
        rxAndroid （基于RxJava) 可以理解为异步处理（后期最好不在使用new Thread()开辟线程）------基于观察者模式思想
        api: https://mcxiaoke.gitbooks.io/rxdocs/content/Intro.html
    2、OKHttp------实现网路请求
        超时时间、日志、文件传输、公共参数等等
    3、retrofit-----可以理解为通过注解方式 对okHttp的一种封装
    4、MVP----------开发思想，逻辑、页面分离 M(model 逻辑层) V(View  页面) P(Presenter 中间层 衔接、弱引用处理，隔离model、view)

3、BottomTabBar升级
    内部使用FragmentTabHost、底部小红点、CustomFragmentTabHost升级（基于FragmentTabHost改造）

4、SmartRefreshLayout升级
    升级为依赖包形式，修改内容SmartRefreshLayout 最后面

5、RrmLite数据库升级(轻量级，易学易用，中小型项目首选，对数据比较严格的，访问量大的，可考虑替换为greenDao)

6、DotTabLayout 本质上就是TabLayout,只是将TabLayout默认的指示器隐藏（高度设置为0，或者颜色设置透明），换成自己的而已，框架未升级（没必要）----以下为TabLayout的源码大致内容
    1、TabLayout 继承HorizontalScrollView  （水平滑动）---继承 FrameLayout--------通俗理解：实际就是水平滑动的 控件
    2、静态内部类Tab： 包含 文字、图片、子布局等参数--------------------------------通俗理解：TabLayout种子布局的 参数类
    3、内部类TabView：TabLayout滑动的子布局,包含了 textView、ImageView、View(扩展外部自定义使用）等参数
        通俗理解：就是一个具有ImageView跟TextView的  LinearLayout布局
        当自定义View被使用时，默认的TextView、ImageView就是被隐藏
    4、内部类SlidingTabIndicator：继承LinearLayout布局-------TabLayout的子布局TabView以及指示器的父类，
    5、指示器实现原理：在SlidingTabIndicator布局中实现--------------------本质上：是绘制一个Drawable来实现的
        1、通过SlidingTabIndicator中的 onLayout()方法 初步获取到指示器的对应的位置 ,通过ViewPage滑动监听器 获取对应的坐标
        2、在SlidingTabIndicator中的draw()方法中绘制 drawable
        3、可以通过修改SlidingTabIndicator中的Paint去修改指示器的颜色、高度---------------系统可修改属性
    6、DotTabLayout指示器实现，参考的系统的实现原理， 扩展：实现新的样式，实现 AnimatedIndicatorInterface接口就可
    注：SlidingTabIndicator为TabLayout的静态私有类，外部不能直接对其进行修改，修改指示器的样子可以通过以下方式
        1、将系统默认的指示器颜色设置为透明或者高度为0，然后外部自己新增一个指示器（本质上 有两个指示器，只是系统的看不见了）---不用修改系统控件，方便使用
        2、通过反射对SlidingTabIndicator进行属性修改---------不稳
        3、通过复制TabLayout的源码，然后在进行修改----------TabLayout升级时，需要手动升级
        4、模仿TabLayout，自定义（本质上跟第3点一样）---网上已有部分框架 如（https://github.com/H07000223/FlycoTabLayout、https://github.com/hackware1993/MagicIndicator）

7、基础类升级
    1、BaseActivity升级：去除多余代码（广播等）
        app退出方式：1、广播，2、Activity管理器，3、杀进程，4、通过newIntent调到HomeActivity，然后finish，5、回调系统首页，进入后台，杀进程
        沉浸模式：1、4.4后：结合toolBar,window添加FLAG_TRANSLUCENT_STATUS,导航布局 android:fitsSystemWindows="true"
                 2、5.0后：可使用setStatusBarColor方法，尴尬的是，只能设置纯颜色
                 3、其实状态栏，本身也是一个View,自己扔一个上去，改成对应颜色就可，可参考 https://github.com/laobie/StatusBarUtil
                 4、在代码中手动设置 第一个View的头部间距 添加paddingTop，BaseFragment就是这么实现的
    2、BaseActivity升级
    3、AppExceptionHelper升级--错误日志---------application中初始化就可
                1、本地保存错误崩溃日志文件，如果由后台，可将该文件 上传
                2、可借助友盟等第三方进行错误日志统计
    4、新增BaseViewInterFace接口，约束BaseActivity、BaseFragment、BaseDialog方法
