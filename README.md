
# AppBase----引用开源框架地址----------------
RxAndroid: https://github.com/ReactiveX/RxAndroid,https://mcxiaoke.gitbooks.io/rxdocs/content/Intro.html
RxJava: https://github.com/ReactiveX/RxJava
OKHttp: https://github.com/square/okhttp
DotTabLayout：https://github.com/anthui/Dachshund-Tab-Layout
ORMLite: http://ormlite.com/releases/
SmartRefreshLayout：https://github.com/scwang90/SmartRefreshLayout



2020-03-06  渠道打包、马甲包--anthui
app_base v1.1.0
开发版本：28.0.0
AS版本:3.6.0
gradle版本:5.6.4

集成内容
    1、app 引入多渠道命令打包美团 瓦力（walle)---上传蒲公英、发送企业通知等尚未集成
    2、app gradle构建不同功能体apk（俗称马甲包、多功能包） 可以配置不同维度，集成不同的功能性代码，引入不同的依赖目录
       src同级项目 可以配置 app(release版）、appDebug(测试目录 debug自动走）
       资源文件： appDebug-debug-app-main
       java文件：公共资源放 main, 差异性资源必须各自放（main中不能存在差异性java代码）如需要可以现在 debug中测试后 在放入 app或者main中
    3、app-base 基础混淆方案

2019-07-01  as版本升级--anthui
开发版本：28.0.0
AS版本:3.4.1
gradle版本:3.4.1
集成内容：
    1、集成兼容库androidx
    2、升级ButterKnife版本10.1.0

2019-06-01  底层库升级--anthui
app_base v1.1.0
开发版本：28.0.0
AS版本:3.4.1
gradle版本:3.4.1
集成内容：
   1、rxAndroid版本升级为2.1.1,会默认引入rxJava_2.2.6版本
   2、Retrofit 版本升级为当前最新版 2.6.0,配套adapter-rxJava、converter-gson升级 2.6.0
   3、OKHttp升级为当前最新版本 4.0.1
   4、Glide升级为 4.9.0
   5、新增 app_test模块----所有的测试内容在这里进行，正式开放时直接移除就可
   6、BottomTabBar升级 内部使用FragmentTabHost,根据需求可替换为 CustomFragmentTabHost
   7、DotTabLayout升级 处理内部多余字段，指示器颜色、高度
   8、AntBanner升级 无限滚动、自动滚动、切换动画、自定义item布局(默认ImageView)等
   9、SmartRefreshLayout升级 v1.1.0-beta-1 依赖包形式升级 目的为了方便修改源码、日后升级
   10、OrmLite数据库升级v_5.1 用户保存采用数据库存储

# AppBase----android开发基础框架
2018-08-01  完成基础框架--anthui  
开发版本：27.0.3
AS版本:3.0
gradle版本:
集成内容：
    1、集成 ButterKnife
    2、集成网路请求 Retrofit+RxAndroid
    3、图片请求Glide
    4、刷新框架smartrefresh
    5、流动布局TagFlowLayout
    6、基础父类 BaseActivity、BaseFragment、BaseAdapter、AntApplication、BaseDialog等基础父类
    7、集成SiZeUtil、LogUtil、StringUtil、CommonUtils等工具类
    8、集成OrmLite数据库
    9、集成第三方基础支付：微信、支付宝
    10、集成推送：个推
    11、其他

