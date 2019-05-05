/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-05-05 10:54:39
 */

package com.mrky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Order;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.OrderRepository;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Map<String, String> addOrder(Integer consumerId, Integer goodsId, Integer orderNumber) {

        Map<String, String> map = new HashMap<>();

        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        Goods goods = goodsRepository.findByGoodsId(goodsId);
        if (goods == null) {
            map.put("msg", "不存在的商品");
            return map;
        }

        Order o = new Order();
        o.setConsumerId(consumerId);
        o.setConsumerName(consumer.getConsumerName());
        o.setGoodsId(goodsId);
        o.setGoodsName(goods.getGoodsName());
        o.setOrderNumber(orderNumber);
        o.setOrderStatus(1);

        orderRepository.save(o);

        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> deleteOrder(Integer consumerId, Integer orderId) {
        return null;
    }

    @Override
    public Map<String, String> ensureOrder(Integer orderId, Integer consumerId) {

        Map<String, String> map = new HashMap<>();
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        if (order.getConsumerId().equals(consumerId) == false) {
            map.put("msg", "不存在对当前订单操作的权限");
            return map;
        }

        return null;
    }

    @Override
    public Map<String, String> lookAmount(Integer consumerId) {
        return null;
    }

    @Override
    public List<Order> lookGoods() {
        return null;
    }

    @Override
    public Map<String, String> registry(String consumerName, String consumerPassword, String address) {
        return null;
    }

    @Override
    public Map<String, String> returnGoods(Integer consumerId, Integer orderId) {
        return null;
    }

    @Override
    public Map<String, String> update(Consumer conumser) {
        return null;
    }

}