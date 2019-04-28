package com.mrky.service;

import java.util.List;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.GoodsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(Integer id) {

    }

    @Override
    public Consumer getConsumer(Integer id) {
        return null;
    }

    @Override
    public Goods getGoods(Integer id) {
        return null;
    }

    @Override
    public Order readOrderById(Integer id) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }

}