/*
 * @Description: 
 * @Autor: Ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-25 17:17:44
 * @LastEditTime: 2019-04-28 14:36:32
 */

package com.mrky.service;

import java.util.List;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface MerchantService {

    Merchant addMerchant(Merchant merchant);

    Merchant readMerchantById(Integer id);

    void updateMerchant(Merchant merchant);

    void deleteMerchant(Integer id);

    // 根据商户id找到所有该商户出售的商品
    List<Goods> findGoods(Integer id);
}