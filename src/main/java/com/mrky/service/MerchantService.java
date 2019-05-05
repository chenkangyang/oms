/*
 * @Description: 
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 17:17:44
 * @LastEditTime: 2019-05-05 17:07:46
 */

package com.mrky.service;

import java.util.List;
import java.util.Map;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.MyOrder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface MerchantService {
    // 功能:
    // 权限要求:
    // 条件:
    // 参数:
    // 操作:

    // 功能: 注册
    // 权限要求: 无
    // 条件:
    // 参数: 商家的name，商家的password
    // 操作: 数据库中添加商户
    Map<String, String> registry(String merchantName, String merchantPassword);

    // 功能: 查看商家的产品
    // 权限要求: 指定商家
    // 条件:
    // 参数: 商家id
    // 操作: 返回goods
    List<Goods> showOwnGoods(Integer merchantId);

    // 功能: 增加新的商品
    // 权限要求: 指定商家
    // 条件:
    // 参数: 商家id， 商品goodsName, goodsPrice, goodsStock
    // 操作: 在OrderRepository中增加指定产品
    Map<String, String> addGoods(Integer merchantId, String goodsName, Integer goodsPrice, Integer goodsStock);

    // 功能: 确认订单？？？，商家有权利？
    // 权限要求:
    // 条件:
    // 参数:
    // 操作

    // 功能: 查看订单
    // 权限要求: 指定商家
    // 条件:
    // 参数: 商家id，
    // 操作: 返回该商户订单
    List<MyOrder> showOrders(Integer merchantId);

    // 功能: 处理退货
    // 权限要求: 指定商家
    // 条件: order的状态为4， 后期可以适当更改，设置枚举类型，用于映射
    // 参数: 商家id，
    // 操作: 更改订单状况
    Map<String, String> allowReturn(Integer merchantId, Integer orderId);

    // 功能: 返回商家收入
    // 权限要求: 指定商家
    // 条件:
    // 参数: 商家id
    // 操作: 以map数据结构的amount 对应值为准
    Map<String, String> lookAmount(Integer merchantId);
}