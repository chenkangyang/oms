package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.mrky.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByOrder_consumerId(Integer id);

    @Query("delete from Order where order_goodsId = :goods_id")
    void deleteByGoodsId(@Param(value = "goods_id") Integer goods_id);
}