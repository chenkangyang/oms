package com.mrky.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.MyOrder;
import com.mrky.service.GoodsServiceImpl;
import com.mrky.service.MerchantServiceImpl;
import com.mrky.service.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
public class MerchantController {

    @Autowired
    private MerchantServiceImpl merchantServiceImpl;

    @RequestMapping(path = "/merchant/registry", method = RequestMethod.POST)
    public Map<String, String> registry(@RequestParam("username") String merchantName,
            @RequestParam("password") String merchantPassword) {

        return merchantServiceImpl.registry(merchantName, merchantPassword);
    }

    @RequestMapping(path = "/merchant/goods", method = RequestMethod.GET)
    public List<Goods> deleteMerchant(@RequestParam Integer merchantId) {

        return merchantServiceImpl.showOwnGoods(merchantId);
    }

    @RequestMapping(path = "/merchant/goods", method = RequestMethod.POST)
    public Map<String, String> addGoods(@RequestParam Integer merchantId, @RequestParam String goodsName,
            @RequestParam Integer goodsPrice, @RequestParam Integer goodsStock) {

        return merchantServiceImpl.addGoods(merchantId, goodsName, goodsPrice, goodsStock);
    }

    @RequestMapping(path = "/merchant/order", method = RequestMethod.GET)
    public List<MyOrder> lookOrder(@RequestParam Integer merchantId) {
        return merchantServiceImpl.showOrders(merchantId);
    }

    @RequestMapping(path = "/merchant/amount", method = RequestMethod.GET)
    public Map<String, String> lookAmount(@RequestParam Integer merchantId) {
        return merchantServiceImpl.lookAmount(merchantId);
    }

    @RequestMapping(path = "/merchant/order", method = RequestMethod.PUT)
    public Map<String, String> allowReturn(@RequestParam Integer merchantId, @RequestParam Integer orderId) {
        return merchantServiceImpl.allowReturn(merchantId, orderId);
    }

}