/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-05-05 13:15:47
 */

package com.mrky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;
import com.mrky.repository.OrderRepository;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Map<String, String> addOrder(Integer consumerId, Integer goodsId, Integer orderNumber) {

        Map<String, String> map = new HashMap<>();

        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        Goods goods = goodsRepository.findByGoodsId(goodsId);
        if (goods == null) {
            map.put("msg", "不存在的商品");
            return map;
        }

        // // 检查客户资金是否足够
        // Integer amount = goods.getGoodsPrice() * orderNumber;
        // if (amount > consumer.getConsumerAmount()) {
        // map.put("msg", "客人不具备足够资金请充值！");
        // return map;
        // }

        // 检查商品库存
        Integer stock = goods.getGoodsStock();
        if (orderNumber > stock) {
            map.put("msg", "商品库存不足");
            return map;
        }

        Integer spends = goods.getGoodsPrice() * orderNumber;

        Order o = new Order();
        o.setConsumerId(consumerId);
        o.setConsumerName(consumer.getConsumerName());
        o.setGoodsId(goodsId);
        o.setGoodsName(goods.getGoodsName());
        o.setOrderNumber(orderNumber);
        o.setOrderStatus(1);
        o.setOrderAmount(spends);
        orderRepository.save(o);

        // 更改客人消费数目
        consumer.setConsumerAmount(consumer.getConsumerAmount() + spends);
        consumerRepository.save(consumer);

        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> deleteOrder(Integer consumerId, Integer orderId) {

        Map<String, String> map = new HashMap<>();
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        if (order.getConsumerId().equals(consumerId) == false) {
            map.put("msg", "不存在对当前订单操作的权限");
            return map;
        }

        // 1：客户下单 -> 2：客人确认收货 -> 3:客人退货 -> 4:商家同意, 只有为状态1时才能取消
        if (order.getOrderStatus() == 1) {
            orderRepository.deleteById(orderId);
        } else {
            map.put("msg", "订单当前状态不允许取消");
        }

        // 消费金额更改
        consumer.setConsumerAmount(consumer.getConsumerAmount() - order.getOrderAmount());
        consumerRepository.save(consumer);

        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> ensureOrder(Integer orderId, Integer consumerId) {

        Map<String, String> map = new HashMap<>();
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        if (order.getConsumerId().equals(consumerId) == false) {
            map.put("msg", "不存在对当前订单操作的权限");
            return map;
        }

        // 检查产品信息
        Goods goods = goodsRepository.findByGoodsId(order.getGoodsId());
        if (goods == null) {
            map.put("msg", "无效的订单，商品信息有误");
            return map;
        }

        // 检查卖家
        Merchant merchant = merchantRepository.findByMerchantId(goods.getMerchantId());
        if (merchant == null) {
            map.put("msg", "该商品的卖家不存在");
            return map;
        }

        // 更改order状态
        // 1：客户下单 -> 2：客人确认收货 -> 3:客人退货 -> 4:商家同意, 只有为状态1时才能删除
        if (order.getOrderStatus() == 1) {
            order.setOrderStatus(2);
        } else {
            map.put("msg", "订单当前状态不允许确认收货");
            return map;
        }

        // 修改卖家收入
        merchant.setMerchantAmount(merchant.getMerchantAmount() + order.getOrderAmount());
        merchantRepository.save(merchant);

        // 保存
        orderRepository.save(order);

        map.put("status", "successful");
        return map;
    }

    @Override
    public Map<String, String> lookAmount(Integer consumerId) {

        return null;
    }

    @Override
    public List<Order> lookGoods() {
        return null;
    }

    @Override
    public Map<String, String> registry(String consumerName, String consumerPassword, String address) {
        return null;
    }

    @Override
    public Map<String, String> returnGoods(Integer consumerId, Integer orderId) {
        return null;
    }

    @Override
    public Map<String, String> update(Consumer conumser) {
        return null;
    }

}