package com.mrky.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.mrky.domain.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByConsumerId(Integer id);

    Consumer findByConsumerName(String consumerName);

}
