package com.mrky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrky.domain.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

}