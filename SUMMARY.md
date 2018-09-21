[文档](README.md)

- mySUMMARY 库的目录文件

### 主页面
- MainActivity 主页面
- TitleBarActivity 自定义标题

- MoBanActivity  界面文件的模板
- activitymoban  模板xml文件

- 接口访问不通有两个原因：1地址不对，2参数错误。


### 控件 方法类的命名规则
- 控件 btn tv img
- 方法  requestDistrict(获得数据) selectDistrict(单击选择)
   - initDatas(数据初始化)



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
- Constant 固定字段

### 思路
**无论是否存在界面，必须先按照模块找到数据**
1. 先把数据写到本地，
2. 在把数据解析成对象
3. 在把数据和界面进行适配。
4. 上班的时间主要是做流程，下班的时间解决技术难题。
5. 做界面时，请做到可视化，修改比较方便。
6. 做界面时，资源文件要做到位，以后复制起来比较方便。
7 先把界面的数据请求到本地，在写界面，界面为数据服务。
8 请求的数据放在每页的注释里面。
### 备注
1. 访问网络的时候，如果现实缺少参数，就是因为采用的get访问，不解析参数，
所以需要把参数写在网址的后面才可以。
2. 写app的时候先把后台的数据接收下来，把数据库的数据建立起来。
3. 只有有了数据，在有了页面。写起来才比较方便。
4. OrderWeb,UserWeb中有HttpPatch访问网络方式account_chstore
5. 学习的目的就是把复杂的事情做的简单化，并且越来越简单。
6. 加载进来的数据一定要经过本地的数据的增删改查，勿相信网络数据。

- 测试类 MathTest
### 3、发货模块 完结
##### 1）、待发货清单  ReceActivity (发货)
##### 2）、发货——发货清单 完结
##### 3）、采购单已包装信息  ShipmentInfoActivity (采购单详情)
##### 4）、完成扫码包装  CompleteDeliver
##### 5）（已弃用）获取可合并的采购单
CaptureActivity  扫描的页面

### 4、商品库存模块  repertory 完结
##### 1）、商品库存  RepertoryManagerActivity   (库存管理)
##### 2）、库存商品搜索  RepertorySearchActivity
##### 3)、待分配库位的商品 RepertoryListsWaitActivity (待分配库位商品)
##### 4)、仓库区域下具体商品  RepertoryGoodActivity (区下商品列表)
##### 5）、库存商品详情   RepertoryDetailActivity  (商品库存)
##### 6）、获取所有库位信息 完结
##### 7）、更改商品库位 完结
##### 8）、商品库存数量位置迁移   RepertoryRemoveActivity 没有标题

### 1、个人模块 User  完结
#####  1）登录  LoginActivity
##### 2）修改密码 ModifyPassWordActivity
##### 3）退出登录  UserCenterActivity


### 2、采购模块
##### 1）、采购申请 ProchActivity (采购申请)
##### 2）、采购明细   完结
##### 3）、通过采购申请 完结
##### 4）、拒绝采购申请  PorchInfoActivity(采购详情)
##### 5）、采购汇总   完结  以下又是一个版块
##### 6）、开始采购  ProchSummaryActivity (采购汇总)
##### 7）、进行中的采购列表 MyProchActivity 独立版块  (我的采购)
##### 8）、进行中的采购详情 完结
##### 9）、结束采购单 PurchaseInfoActivity  (采购详情)
##### 10）、搜索商品  SeachGoodActivity  GoodsSearchActivity
##### 11）、门店采购统计