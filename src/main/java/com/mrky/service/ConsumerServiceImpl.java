/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-04-28 15:06:43
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

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Consumer addConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
        return consumer;
    }

    @Override
    public void deleteConsumer(Integer id) {
        // 删除用户同时，也要删除与其相关的order
        consumerRepository.deleteById(id);

    }

    @Override
    public List<Order> findOrder(Consumer consumer) {

        return orderRepository.findByConsumerId(consumer.getId());
    }

    @Override
    public Consumer readConsumerById(Integer id) {
        return consumerRepository.findByConsumerId(id);
    }

    @Override
    public void updateConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

}