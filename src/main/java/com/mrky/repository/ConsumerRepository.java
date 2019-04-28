package com.mrky.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.mrky.domain.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("select c from Consumer c where c.id = : ConsumerID")
    Consumer findByConsumerId(@Param(value = "ConsumerID") Integer id);
}
