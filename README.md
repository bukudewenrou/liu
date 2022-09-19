# 宁波房产公众版
##### 包名： com.zcitc.cloudhousehelper
##### 语言： kotlin + java （kotlin优先）
##### 项目架构：MVP
##### 核心框架：okgo rxkotlin
##### 目录结构：
```
project
  ├─ apshare : 支付宝授权回调
  ├─ wxapi : 微信授权回调
  ├─ dao : GreenDao相关类，由GreenDao自动生成
  ├─ base : 基类等基础组件
  ├─ constants : 请求地址，intent事件tag等常量存储
  ├─ helper : 存放各种工具类
  └─ mvp : MVP架构文件
      ├─ contract : Model层和View层接口定义处
      ├─ model : Model层的具体实现
      ├─ presenter
      └─ ui : View层相关
          ├─ activity
          ├─ fragment
          ├─ adapter
          └─ widget : 自定义组件
```
##### 架构实现实例：
1. 添加contract类
2. 添加model类并继承`BaseModel`和实现Contract中的Model接口
3. 添加presenter类并继承`BasePresenter`，注意实现抽象变量`mModel`
4. 添加activity/fragment继承`BaseActivity/Fragment`和实现Contract中的View接口，注意使用`by lazy`实现`mPresenter`
