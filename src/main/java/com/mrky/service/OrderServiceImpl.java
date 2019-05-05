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

}