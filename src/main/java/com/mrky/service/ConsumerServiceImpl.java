package com.mrky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrky.domain.Consumer;
import com.mrky.domain.Goods;
import com.mrky.repository.ConsumerRepository;
import com.mrky.repository.OrderRepository;
import com.mrky.exception.ConflictException;
import com.mrky.exception.NotFoundException;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ConsumerServiceImpl(ConsumerRepository consumerRepository, PasswordEncoder encoder) {
        this.consumerRepository = consumerRepository;
        this.encoder = encoder;
    }

    @Override
    public Consumer addConsumer(Consumer consumer) {
        try {
            consumerRepository.findById(consumer.getId()).ifPresent(t -> {
                throw new ConflictException();
            });
        } catch (ConflictException e) {
            return null;
        }
        consumer.setPassword(encoder.encode(consumer.getPassword()));
        return consumerRepository.save(consumer);
    }

    @Override
    public void deleteConsumer(Integer id) {
        try {
            if (!consumerRepository.existsById(id)) {
                throw new NotFoundException();
            }
        } catch (NotFoundException e) {

        }
    }

    @Override
    public List<Goods> findGoods(Integer id) {
        return null;
    }

    @Override
    public void resetPassword(String password) {

    }

    @Override
    public void updateConsumer(Consumer consumer) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}