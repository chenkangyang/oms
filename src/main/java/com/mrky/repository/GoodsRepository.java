package com.mrky.repository;

import org.springframework.data.repository.CrudRepository;

import com.mrky.domain.Goods;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {

}