package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mrky.domain.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    Merchant findByMerchantId(@Param(value = "merchantId") Integer id);

    Merchant findByMerchantName(String merchantName);

}