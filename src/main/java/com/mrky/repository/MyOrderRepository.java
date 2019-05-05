package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.mrky.domain.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {

    List<MyOrder> findByConsumerId(Integer consumerId);

    MyOrder findByOrderId(Integer orderId);

    List<MyOrder> findByGoodsId(List<Integer> goodsId);
}