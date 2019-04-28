/*
 * @Description: In User Settings Edit
 * @Author: ran Meng
 * @LastEditors: Ran Meng
 * @Date: 2019-04-24 23:05:58
 * @LastEditTime: 2019-04-28 14:38:52
 */

package com.mrky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.domain.Order;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.OrderRepository;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public Consumer addConsumer(Consumer consumer) {
        return null;
    }

    @Override
    public void deleteConsumer(Integer id) {

    }

    @Override
    public List<Order> findOrder(Consumer consumer) {
        return null;
    }

    @Override
    public Consumer readConsumerById(Integer id) {
        return null;
    }

    @Override
    public void resetPassword(String password) {

    }

    @Override
    public void updateConsumer(Consumer consumer) {

    }

}