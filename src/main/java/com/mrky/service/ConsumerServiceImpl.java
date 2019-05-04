/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-05-04 23:19:36
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
import com.mrky.repository.OrderRepository;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String, String> addOrder(Integer consumerId, Integer goodsId, Integer number) {
        return null;
    }

    @Override
    public Map<String, String> deleteOrder(Integer consumerId, Integer orderId) {
        return null;
    }

    @Override
    public Map<String, String> ensureOrder(Integer OrderId, Integer consumerId) {
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