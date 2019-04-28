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
public class MerchantServiceImpl implements MerchantService {

    @Override
    public Merchant addMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public void deleteMerchant(Integer id) {

    }

    @Override
    public List<Goods> findGoods(Integer id) {
        return null;
    }

    @Override
    public Merchant readMerchantById(Integer id) {
        return null;
    }

    @Override
    public void updateMerchant(Merchant merchant) {

    }

}