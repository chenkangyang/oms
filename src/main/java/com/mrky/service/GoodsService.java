/*
 * @Description: 
 * @Version: 2.0
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 21:25:29
 * @LastEditTime: 2019-04-28 14:34:53
 */

package com.mrky.service;

import java.util.List;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;

public interface GoodsService {

    // create read update delete
    Goods addGoods(Goods goods);

    Goods readGoodsById(Integer id);

    void deleteGoods(Integer id);

    void updateGoods(Goods goods);

    // 根据商品id找到其卖家
    Merchant getMerchant(Integer id);

    // 根据商品id找到所有有关该商品的order
    List<Order> getOrders(Integer id);
}