/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-05-05 22:11:33
 */

package com.mrky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrky.domain.Consumer;
import com.mrky.domain.ConsumerDetails;
import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.MyOrder;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;
import com.mrky.repository.MyOrderRepository;
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
    private MyOrderRepository orderRepository;

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

        MyOrder o = new MyOrder();
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
        MyOrder order = orderRepository.findByOrderId(orderId);
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

        // goods信息修改
        Goods goods = goodsRepository.findByGoodsId(order.getGoodsId());
        goods.setGoodsStock(goods.getGoodsStock() + order.getOrderNumber());
        goodsRepository.save(goods);

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
        MyOrder order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        if (order.getConsumerId().equals(consumerId) == false) {
            map.put("msg", "不存在对当前订单操作的权限");
            return map;
        }

        // // 检查产品信息
        Goods goods = goodsRepository.findByGoodsId(order.getGoodsId());
        // if (goods == null) {
        // map.put("msg", "无效的订单，商品信息有误");
        // return map;
        // }

        // // 检查卖家
        Merchant merchant = merchantRepository.findByMerchantId(goods.getMerchantId());
        // if (merchant == null) {
        // map.put("msg", "该商品的卖家不存在");
        // return map;
        // }

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
        Map<String, String> map = new HashMap<>();
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }

        // 返回amount信息
        map.put("amount", String.valueOf(consumer.getConsumerAmount()));

        return map;
    }

    @Override
    public List<Goods> lookGoods() {

        // 目前不考虑分页，全部返回
        List<Goods> list = goodsRepository.findAll();

        return list;
    }

    @Override
    public Map<String, String> registry(String consumerName, String consumerPassword, String consumerAddress) {

        Map<String, String> map = new HashMap<>();

        if (consumerName == null || consumerName.length() == 0) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (consumerPassword == null || consumerName.length() == 0) {
            map.put("msg", "用户密码不能为空");
            return map;
        }

        Consumer consumer = consumerRepository.findByConsumerName(consumerName);
        if (consumer != null) {
            map.put("msg", "用户名重复");
            return map;
        }

        Consumer c = new Consumer();
        c.setConsumerName(consumerName);
        c.setConsumerPassword(consumerPassword);
        c.setConsumerAddress(consumerAddress);
        c.setConsumerAmount(0); // 初始消费金额为0
        consumerRepository.save(c);

        map.put("status", "successful");
        return map;
    }

    // 退货
    @Override
    public Map<String, String> returnGoods(Integer consumerId, Integer orderId) {
        Map<String, String> map = new HashMap<>();
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            map.put("msg", "不存在的客户");
            return map;
        }
        MyOrder order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            map.put("msg", "不存在的订单");
            return map;
        }

        if (order.getConsumerId().equals(consumerId) == false) {
            map.put("msg", "不存在对当前订单操作的权限");
            return map;
        }

        // 更改order状态
        // 1：客户下单 -> 2：客人确认收货 -> 3:客人退货 -> 4:商家同意, 只有为状态1时才能删除
        if (order.getOrderStatus() == 2) {
            order.setOrderStatus(3);
        } else {
            map.put("msg", "当前订单不允许退货");
            return map;
        }

        // 修改商家的收入
        // Goods goods = goodsRepository.findByGoodsId(order.getGoodsId());
        // Merchant merchant =
        // merchantRepository.findByMerchantId(goods.getMerchantId());

        orderRepository.save(order);

        map.put("status", "successful");
        return map;
    }

    @Override
    public List<MyOrder> lookOrders(Integer consumerId) {
        Consumer consumer = consumerRepository.findByConsumerId(consumerId);
        if (consumer == null) {
            // 不存在该用户
            return null;
        }
        List<MyOrder> list = orderRepository.findByConsumerId(consumerId);
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Consumer consumer = consumerRepository.findByConsumerName(username);
        if (consumer == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        return new ConsumerDetails(consumer);
    }

}