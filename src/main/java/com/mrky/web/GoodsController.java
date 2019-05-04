package com.mrky.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.service.GoodsServiceImpl;
import com.mrky.service.MerchantServiceImpl;
import com.mrky.service.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @Autowired
    private MerchantServiceImpl merchantServiceImpl;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping(path = "/merchant/goods/add", method = RequestMethod.POST)
    public String addNewGoods(@RequestParam Integer id, @RequestParam String name, @RequestParam String imgUrl,
            @RequestParam Double price, @RequestParam Integer number, @RequestParam Integer merchant_id,
            @RequestParam Integer order_id) {

        return null;
    }

    @RequestMapping(path = "/merchant/goods/delete", method = RequestMethod.POST)
    public String deleteGoods(@RequestParam Integer id) {
        goodsServiceImpl.deleteGoods(id);
        return null;
    }

    @RequestMapping(path = "/merchant/goods/read", method = RequestMethod.POST)
    public Goods readGoodsById(@RequestParam Integer id) {
        return goodsServiceImpl.readGoodsById(id);
    }

    @RequestMapping(path = "/merchant/goods/update", method = RequestMethod.POST)
    public Goods updateGoods(@RequestParam Integer id, @RequestParam String name, @RequestParam String imgUrl,
            @RequestParam Double price, @RequestParam Integer number, @RequestParam Integer merchant_id) {
        return null;
    }

}