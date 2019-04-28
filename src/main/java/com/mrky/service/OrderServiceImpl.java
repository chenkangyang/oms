package com.mrky.service;

import java.util.List;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteByOrderId(id);
    }

    @Override
    public Consumer getConsumer(Integer id) {
        return consumerRepository.findByConsumerId(id);
    }

    @Override
    public Goods getGoods(Integer id) {

        return goodsRepository.findByGoodsId(id);
    }

    @Override
    public Order readOrderById(Integer id) {
        return orderRepository.findByOrderId(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

}