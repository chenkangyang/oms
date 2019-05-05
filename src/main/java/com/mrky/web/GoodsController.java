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

}