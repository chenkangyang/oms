package com.mrky.service;

import java.util.List;

import com.mrky.domain.Goods;
import com.mrky.domain.Merchant;
import com.mrky.domain.Order;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;
import com.mrky.repository.GoodsRepository;
import com.mrky.repository.MerchantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(Integer id) {
        merchantRepository.deleteById(id);
        // 删除所有引用该merchant的商品？？？
    }

    @Override
    public List<Goods> findGoods(Integer id) {
        return goodsRepository.findByMerchantId(id);
    }

    @Override
    public Merchant readMerchantById(Integer id) {
        return merchantRepository.findByMerchantId(id);
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

}