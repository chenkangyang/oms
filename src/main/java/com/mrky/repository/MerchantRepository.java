package com.mrky.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.repository.CrudRepository;

import com.mrky.domain.Merchant;

public interface MerchantRepository extends CrudRepository<Merchant, Integer> {

}