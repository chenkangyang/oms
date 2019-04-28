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
public class MerchantController {

    @Autowired
    private MerchantServiceImpl merchantServiceImpl;

    @RequestMapping(path = "/merchant/add", method = RequestMethod.POST)
    public @ResponseBody String addMerchant(@RequestParam Integer id, @RequestParam String password,
            @RequestParam Integer income) {

        merchantServiceImpl.addMerchant(new Merchant(id, password, income));
        return "Add one merchant";
    }

    @RequestMapping(path = "/merchant/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteMerchant(@RequestParam Integer id) {

        merchantServiceImpl.deleteMerchant(id);
        return "Delete one merchant";
    }

    @RequestMapping(path = "/merchant/update", method = RequestMethod.POST)
    public @ResponseBody String updateMerchant(@RequestParam Integer id, @RequestParam String password,
            @RequestParam Integer income) {

        merchantServiceImpl.updateMerchant(new Merchant(id, password, income));
        return "Add one merchant";
    }

}