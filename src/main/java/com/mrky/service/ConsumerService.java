package com.mrky.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;

import java.util.List;

public interface ConsumerService extends UserDetailsService {

    Consumer addConsumer(Consumer consumer);

    void updateConsumer(Consumer consumer);

    void deleteConsumer(Integer id);

    void resetPassword(String password);

    List<Goods> findGoods(Integer id);
}