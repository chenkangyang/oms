package com.mrky.service;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;

public interface GoodsService {

    Goods addGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateGoods(Goods goods);

    Merchant getMerchant(Goods goods);

    <Collection> Order getOrders(Integer id);
}