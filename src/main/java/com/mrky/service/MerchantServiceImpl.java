package com.mrky.service;

import java.util.List;
import java.util.Map;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;
import com.mrky.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String, String> allowReturn(Integer merchantId, Integer orderId) {
        return null;
    }

    @Override
    public Map<String, String> lookAmount(Integer merchantId) {
        return null;
    }

    @Override
    public Map<String, String> registry(String merchantName, String merchantPassword) {
        return null;
    }

    @Override
    public List<Order> showOrders(Integer merchantId) {
        return null;
    }

    @Override
    public List<Goods> showOwnGoods(Integer merchantId) {
        return null;
    }
}