[目录](mySUMMARY.md)

> 工作的流程
- 对于难点，一定不要死磕，要绕过，做好记录，已保证进度。
- 周六日要攻克难点，上班时间保证进度。这样比较有效率。
> 应用的原理
- 学项目要做到 功能清晰，结构明确
- 项目要划分业务模块，比较清晰
- 类的代码要分清结构，业务层主管业务，主导器层主导方法，适配器层适配条目。
- 控件的名字要做好常规命名，方便搜索。
- 因为项目的变动比较大，所以重构会比较频繁，一定要方便找东西。
    - ctrl+N 查找类  ctrl+shift+N 查找文件
- 底部时间滚动弹出框原理
    - 底部弹框 封装的是弹出框
    - 滚动轮是一个封装的view控件
- Handle 是在开启子线程访问网络的时候，子线程和主线程进行通讯和传递数据的类。
- 弹出框提示的文字有点问题 toast前总是有仓库端。 把清单文件中的label去掉就可以了