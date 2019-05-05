package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.mrky.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("delete from Order o where o.order_goodsId = :goods_id")
    void deleteByGoodsId(@Param(value = "goods_id") Integer goods_id);

    @Query("select o from Order o where o.order_goodsId = : goods_id")
    List<Order> findByGoodsId(@Param(value = "goods_id") Integer id);

    @Query("delete from Order o where o.order_id = :order_id")
    void deleteByOrderId(@Param(value = "order_id") Integer order_id);

    @Query("select o from Order o where o.order_id = :order_id")
    Order findByOrderId(@Param(value = "order_id") Integer order_id);

    List<Order> findByConsumerId(Integer consumerId);

    List<Order> findByMerchantId(Integer merchantId);

}