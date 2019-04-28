/*
 * @Description: 
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 21:25:43
 * @LastEditTime: 2019-04-28 14:38:38
 */

package com.mrky.service;

import com.mrky.domain.Order;
import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;

public interface OrderService {

    Order addOrder(Order order);

    Order readOrderById(Integer id);

    void deleteOrder(Integer id);

    void updateOrder(Order order);

    // 根据订单id找到买家
    Consumer getConsumer(Integer id);

    // 根据订单id找到商品
    Goods getGoods(Integer id);
}