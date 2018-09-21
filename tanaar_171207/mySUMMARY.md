[文档](myREADME.md)

- activity 界面文件夹
    - SplashScreenActivity 引导页面
    - MoBanActivity 界面文件的模板
    - activitymoban  模板xml文件
- view 专业工具包
    - TopBarView  自定义顶部导航栏
- util_Jar 专业工具包
    - AppUtils App相关工具类,例如版本号
- 自定义弹出框
    - PopShowImageDialog  显示图片的弹出框
- customview 控件重新定义
    - ImageGallery 重新定义的轮播图
- myzxing 扫描工具包
    - CaptureActivity 扫描的页面
- util 常用工具包
    - HttpPatch  patch访问接口的方式
    - ImagesUtil_me 这个是图片相关的工具类
    - JsonUtil  json和对象的相互转化
- resourse 资源
    - [Mydialog弹出框](resourse/Mydialog弹出框.md)
    - [照相功能的实现](resourse/照相功能的实现.md)
    - [自定义apk命名](resourse/自定义apk命名.md)
    - [studio配置](resourse/studio配置.md)

以下是其他地方引用过来的
### 其他模块
- CeShiJieKou 测试接口使用
- WheelSex 底部弹出框的时间滚动轴
- MyReceiver  推送的广播接收器
- MyListView 自定义的列表控件
### 资源
- themes 主题
- styles 样式
- dimens  尺寸
- 字体大小规格 一般用12sp
- 颜色
- drawable 自定义的背景用shape开头
- AndroidManifest 清单文件
- 图片的格式   android:scaleType="fitXY"
- shape_bg.xml  自定义背景
- edit_search_bg.xml  自定义编辑框背景

### 思路
**在没有界面的情况下，可以按模块连接数据**
1. 先把数据写到本地，
2. 在把数据解析成对象
3. 在把数据和界面进行适配。
4. 上班的时间主要是做流程，下班的时间解决技术难题。
5. 做界面时，请做到可视化，修改比较方便。
6. 做界面时，资源文件要做到位，以后复制起来比较方便。
7. 学习新技术最快的方式，是直接单独建立一个demo，
把别人的代码跑起来。例如 CeShiWarehouse。
这样就可以方便的打开测试的demo学习新技术了。
不要希望看明白原理了，在去实现。只要能跑起来就能够实现。
8. 变量和方法和类一定要先用，利用ide工具自动创立。
### 备注
1. 访问网络的时候，如果现实缺少参数，就是因为采用的get访问，不解析参数，
所以需要把参数写在网址的后面才可以。
2. 写app的时候先把后台的数据接收下来，把数据库的数据建立起来。
3. 只有有了数据，在有了页面。写起来才比较方便。
4. OrderWeb,UserWeb中有HttpPatch访问网络方式account_chstore




