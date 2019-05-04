/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 22:41:55
 * @LastEditTime: 2019-05-04 22:14:26
 */
package com.mrky.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Order;

import java.util.List;
import java.util.Map;

public interface ConsumerService {

    Map<String, String> registry(String consumerName, String consumerPassword, String address);

    Map<String, String> update(Consumer conumser);

    // 根据consumerId显示其订单
    List<Order> showOrders(Integer consumerId);

    // 根据客人id，商品id，数量创建订单
    Map<String, String> addOrder(Integer consumerId, Integer goodsId, Integer number);

    // 根据商品订单id删除订单,只有未收货才可以删除，否则只能走退货流程
    Map<String, String> deleteOrder(Integer orderId);

    // 修改订单，暂时不写

    // 根据订单id退货只有完成交易后，才可以退货
    Map<String, String> retrunGoods(Integer orderId);

    // 确定订单，一般客人查看自己的订单列表后，根据订单id，同时还要确认客人是否有权限
    Map<String, String> ensureOrder(Integer OrderId, Integer consumerId);

    // 浏览商品,暂时不添加关键词浏览
    List<Order> lookGoods();

    // 查看消费金额，只有客人权限正确才能看，提供信息客人id
    Map<String, String> lookAmount(Integer consumerId);
}