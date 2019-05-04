package com.mrky.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.domain.Consumer;
import com.mrky.service.ConsumerServiceImpl;
import com.mrky.service.GoodsServiceImpl;
import com.mrky.service.MerchantServiceImpl;
import com.mrky.service.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl;

    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping(path = "/order/add", method = RequestMethod.POST)
    public String addOrder(@RequestParam Integer id, @RequestParam Integer consumer_id, @RequestParam Integer goods_id,
            @RequestParam Integer number) {

        return null;
    }
}