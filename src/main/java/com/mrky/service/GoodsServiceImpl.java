/*
 * @Description: 
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 21:26:03
 * @LastEditTime: 2019-04-25 22:35:55
 */

package com.mrky.service;

import java.util.List;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.GoodsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Override
    public Goods addGoods(Goods goods) {
        return null;
    }

    @Override
    public void deleteGoods(Integer id) {

    }

    @Override
    public Merchant getMerchant(Integer id) {
        return null;
    }

    @Override
    public List<Order> getOrders(Integer id) {
        return null;
    }

    @Override
    public Goods readGoodsById(Integer id) {
        return null;
    }

    @Override
    public void updateGoods(Goods goods) {

    }

}