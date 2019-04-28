package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrky.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}