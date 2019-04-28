package com.mrky.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.service.GoodsServiceImpl;
import com.mrky.service.MerchantServiceImpl;
import com.mrky.service.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(path = "/app")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @Autowired
    private MerchantServiceImpl merchantServiceImpl;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping(path = "/merchant/goods/add", method = RequestMethod.POST)
    public @ResponseBody String addNewGoods(@RequestParam Integer id, @RequestParam String name,
            @RequestParam String imgUrl, @RequestParam Double price, @RequestParam Integer number,
            @RequestParam Integer merchant_id, @RequestParam Integer order_id) {

        Merchant merchant = new Merchant();
        merchant.setId(merchant_id);
        Order order = new Order();
        order.setId(order_id);

        Goods g = new Goods(id, name, imgUrl, price, number, merchant, order);
        goodsServiceImpl.addGoods(g);
        return "Saved " + g.toString();
    }

    @RequestMapping(path = "/merchant/goods/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteGoods(@RequestParam Integer id) {
        goodsServiceImpl.deleteGoods(id);
        return "delete " + id + " goods";
    }

    @RequestMapping(path = "/merchant/goods/read", method = RequestMethod.POST)
    public @ResponseBody Goods readGoodsById(@RequestParam Integer id) {
        return goodsServiceImpl.readGoodsById(id);
    }

    @RequestMapping(path = "/merchant/goods/update", method = RequestMethod.POST)
    public @ResponseBody Goods updateGoods(@RequestParam Integer id, @RequestParam String name,
            @RequestParam String imgUrl, @RequestParam Double price, @RequestParam Integer number,
            @RequestParam Integer merchant_id, @RequestParam Integer order_id) {
        Merchant merchant = merchantServiceImpl.readMerchantById(merchant_id);
        Order order = orderServiceImpl.readOrderById(order_id);
        Goods g = goodsServiceImpl.readGoodsById(id);
        g.setName(name);
        g.setImgUrl(imgUrl);
        g.setPrice(price);
        g.setNumber(number);
        g.setMerchant(merchant);
        g.setOrder(order);
        goodsServiceImpl.updateGoods(g);
        return g;
    }

}