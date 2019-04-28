package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrky.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

}