package com.mrky.repository;

import org.springframework.data.repository.CrudRepository;

import com.mrky.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}