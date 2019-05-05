/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 22:41:55
 * @LastEditTime: 2019-05-05 17:43:26
 */
package com.mrky.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.MyOrder;

import java.util.List;
import java.util.Map;

public interface ConsumerService extends UserDetailsService {
    // 功能: 注册
    // 权限要求: 无
    // 条件: 客人用户名不重复
    // 参数: 客人username，客人password， 客人address
    // 操作: 将用户添加到数据库，修改consumer数据库
    Map<String, String> registry(String consumerName, String consumerPassword, String address);

    // 后续
    // 功能: 更新
    // 权限要求: 指定商家
    // 条件:
    // 参数: 商家id
    // 操作: 修改consumer数据库
    // Map<String, String> update(Consumer conumser);

    // 功能: 购买
    // 权限要求: 指定客人
    // 条件: 该产品存在，且库存 stcok > number
    // 参数: 客人consumerId, 商品goodsId，订单数目number
    // 操作: 将goods 的stock减少，增加订单，暂时不允许修改客户的消费以及商家的收入，只有交易完成
    Map<String, String> addOrder(Integer consumerId, Integer goodsId, Integer orderNumber);

    // 功能: 删除订单
    // 权限要求: 指定客人
    // 条件: order状态合乎要求
    // 参数: 客人consumerId, orderId
    // 操作: 将goods 的stock减少，增加订单，反操作
    // 根据商品订单id删除订单,只有未收货才可以删除，否则只能走退货流程
    Map<String, String> deleteOrder(Integer consumerId, Integer orderId);

    // 修改订单，暂时不写

    // 功能: 退货
    // 权限要求: 指定客人
    // 条件: order状态合乎要求
    // 参数: 客人consumerId, orderId
    // 操作: 将goods 的stock减少，增加订单，修改客户的消费以及商家的收入，反操作
    // 根据订单id退货只有完成交易后，才可以退货
    Map<String, String> returnGoods(Integer consumerId, Integer orderId);

    // 功能: 确认收货
    // 权限要求: 指定客人
    // 条件: order状态合乎要求
    // 参数: 客人consumerId, orderId
    // 操作: 修改客户的消费以及商家的收入，此时资金可以流入商家
    // 确定订单，一般客人查看自己的订单列表后，根据订单id，同时还要确认客人是否有权限
    Map<String, String> ensureOrder(Integer OrderId, Integer consumerId);

    // 功能: 浏览商品
    // 权限要求: 无
    // 条件: 无
    // 参数:
    // 操作: 查询商品
    // 浏览商品,暂时不添加关键词浏览
    List<Goods> lookGoods();

    // 功能: 浏览订单
    // 权限要求: 指定客户
    // 条件: 无
    // 参数:
    List<MyOrder> lookOrders(Integer consumerId);

    // 功能: 查看消费
    // 权限要求: 指定客人
    // 条件:
    // 参数:
    // 操作: 商品未交易也会扣钱，就当平台充当中间商，钱会存在平台，客户收货，转入商家账户
    // 查看消费金额，只有客人权限正确才能看，提供信息客人id
    Map<String, String> lookAmount(Integer consumerId);
}