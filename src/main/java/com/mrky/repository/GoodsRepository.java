package com.mrky.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mrky.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    @Query("select g from Goods g where g.id = :goods_id")
    Goods findByGoodsId(@Param(value = "goods_id") Integer id);

    @Query("select g from Goods g where g.goods_merchantId = :merchant_id")
    List<Goods> findByMerchantId(@Param(value = "merchant_id") Integer id);
}