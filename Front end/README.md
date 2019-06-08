# 家具订单管理系统(OMS)前端

> 此文件夹是代码编译后的输出结果. 项目采用前后端分离的开发模式, 前端负责渲染界面, 路由跳转控制和请求接口; 后端负责数据库交互以及提供接口

进入主界面首先判断是否登录, 未登录则跳转到登录界面;
跳转无权限的路由，则会重定向到404界面

## 登录界面
![login.gif](http://blog.nuptalex.xyz/login.gif)

根据登录用户的权限不同, 提供不同的展示界面

## 用户界面


- 展示支出
- 展示订单数
- 展示所有订单
- 浏览所有商品

### 下订单

- 提交下订单表单

## 卖家界面

- 展示所有订单
- 展示收入
- 展示所有商品

### 发货

![changeState.gif](http://blog.nuptalex.xyz/changeState.gif)

### 商品上架

![up.gif](http://blog.nuptalex.xyz/up.gif)

### 商品下架
![down.gif](http://blog.nuptalex.xyz/down.gif)
