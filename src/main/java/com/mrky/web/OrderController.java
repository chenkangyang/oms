package com.mrky.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
@RequestMapping(path = "/app")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl;

    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping(path = "/order/add", method = RequestMethod.POST)
    public @ResponseBody String addOrder(@RequestParam Integer id, @RequestParam Integer consumer_id,
            @RequestParam Integer goods_id, @RequestParam Integer number) {

        Consumer c = consumerServiceImpl.readConsumerById(consumer_id);
        Goods g = goodsServiceImpl.readGoodsById(goods_id);

        orderServiceImpl.addOrder(new Order(id, number, c, g));
        return "Add one merchant";
    }
}