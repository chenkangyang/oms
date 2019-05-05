package com.mrky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.MyOrder;
import com.mrky.repository.ConsumerRepository;
import com.mrky.exception.*;

import com.mrky.service.ConsumerServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl;

    // 注册
    @RequestMapping(path = "/consumer/registry", method = RequestMethod.POST)
    public String registry(@RequestParam("username") String consumerName,
            @RequestParam("password") String consumerPassword, @RequestParam("address") String consumerAddress) {

        consumerServiceImpl.registry(consumerName, consumerPassword, consumerAddress);
        return "registry";
    }

    @RequestMapping(path = "/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.POST)
    public Map<String, String> addOrder(@RequestParam Integer consumerId, @RequestParam Integer orderNumber,
            @RequestParam Integer goodsId) {

        return consumerServiceImpl.addOrder(consumerId, goodsId, orderNumber);
    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.DELETE)
    public Map<String, String> deleteOrder(@RequestParam Integer consumerId, @RequestParam Integer orderId) {
        return consumerServiceImpl.deleteOrder(consumerId, orderId);
    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.PUT)
    public Map<String, String> ensureOrder(@RequestParam Integer consumerId, @RequestParam Integer orderId) {
        return consumerServiceImpl.ensureOrder(orderId, consumerId);
    }

    @RequestMapping(path = "/consumer/amount", method = RequestMethod.GET)
    public Map<String, String> getAmount(@RequestParam Integer consumerId) {
        return consumerServiceImpl.lookAmount(consumerId);
    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.GET)
    public List<MyOrder> findOrder(@RequestParam Integer consumerId) {

        return consumerServiceImpl.lookOrders(consumerId);
    }

    @RequestMapping(path = "/goods", method = RequestMethod.GET)
    public List<Goods> findGoods() {
        return consumerServiceImpl.lookGoods();
    }

}