package com.mrky.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mrky.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    Goods findByGoodsId(Integer goodsId);

    List<Goods> findByMerchantId(Integer merchantId);
}