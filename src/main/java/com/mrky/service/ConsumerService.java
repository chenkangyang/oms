/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 22:41:55
 * @LastEditTime: 2019-04-28 14:32:57
 */
package com.mrky.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Order;

import java.util.List;

public interface ConsumerService {

    // create read update delete
    Consumer addConsumer(Consumer consumer);

    Consumer readConsumerById(Integer id);

    void updateConsumer(Consumer consumer);

    void deleteConsumer(Integer id);

    void resetPassword(String password);

    // 根据consumer信息找到所有有关该consumer的订单信息
    List<Order> findOrder(Consumer consumer);
}