/*
 * @Description: 
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 21:26:03
 * @LastEditTime: 2019-05-05 15:27:10
 */

package com.mrky.service;

import java.util.List;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;
import com.mrky.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    // 删除一件商品，同时一个也要删除所有引用其id的order
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MerchantRepository merchantRepository;

}