package com.mrky.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.MyOrder;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;
import com.mrky.repository.MyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private MyOrderRepository orderRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public Map<String, String> allowReturn(Integer merchantId, Integer orderId) {
        Map<String, String> map = new HashMap<>();
        Merchant merchant = merchantRepository.findByMerchantId(merchantId);
        if (merchant == null) {
            map.put("msg", "不存在的商家");
            return map;
        }
        MyOrder order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        Goods goods = goodsRepository.findByGoodsId(order.getGoodsId());

        if (goods.getMerchantId().equals(merchantId) == false) {
            map.put("msg", "当前商家不存在对当前订单操作的权限");
            return map;
        }

        // 同意退货，修改商家收入以及顾客的消费
        Consumer consumer = consumerRepository.findByConsumerId(order.getConsumerId());
        consumer.setConsumerAmount(consumer.getConsumerAmount() - order.getOrderAmount());
        merchant.setMerchantAmount(merchant.getMerchantAmount() - order.getOrderAmount());

        consumerRepository.save(consumer);
        merchantRepository.save(merchant);

        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> lookAmount(Integer merchantId) {

        Map<String, String> map = new HashMap<>();
        Merchant merchant = merchantRepository.findByMerchantId(merchantId);
        if (merchant == null) {
            map.put("msg", "不存在的商家");
            return map;
        }

        map.put("amount", String.valueOf(merchant.getMerchantAmount()));
        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> registry(String merchantName, String merchantPassword) {

        Map<String, String> map = new HashMap<>();

        if (merchantName == null || merchantName.length() == 0) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (merchantPassword == null || merchantPassword.length() == 0) {
            map.put("msg", "用户密码不能为空");
            return map;
        }

        Merchant merchant = merchantRepository.findByMerchantName(merchantName);
        if (merchant != null) {
            map.put("msg", "用户名重复");
            return map;
        }

        Merchant m = new Merchant();
        m.setMerchantName(merchantName);
        m.setMerchantPassword(merchantPassword);
        m.setMerchantAmount(0); // 初始值

        // 保存
        merchantRepository.save(m);

        map.put("status", "successful");
        return map;
    }

    @Override
    public List<MyOrder> showOrders(Integer merchantId) {

        Merchant merchant = merchantRepository.findByMerchantId(merchantId);
        if (merchant == null) {
            return null;
        }
        List<Goods> goods = goodsRepository.findByMerchantId(merchantId);
        List<Integer> goodsId = new ArrayList<>();
        for (Goods g : goods) {
            goodsId.add(g.getGoodsId());
        }
        List<MyOrder> list = orderRepository.findByGoodsId(goodsId);

        return list;
    }

    @Override
    public List<Goods> showOwnGoods(Integer merchantId) {
        Merchant merchant = merchantRepository.findByMerchantId(merchantId);
        if (merchant == null) {
            return null;
        }

        List<Goods> list = goodsRepository.findByMerchantId(merchantId);
        return list;
    }

    @Override
    public Map<String, String> addGoods(Integer merchantId, String goodsName, Integer goodsPrice, Integer goodsStock) {

        Map<String, String> map = new HashMap<>();
        Merchant merchant = merchantRepository.findByMerchantId(merchantId);
        if (merchant == null) {
            map.put("msg", "不存在的商家");
            return null;
        }

        // 对参数检查。。。，目前算了吧。。。

        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsStock(goodsStock);
        goods.setMerchantId(merchantId);

        goodsRepository.save(goods);

        map.put("status", "successful");
        return map;
    }
}