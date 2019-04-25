package com.mrky.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Order {

    // Order id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // it refers to the consumer entity's id, which can't be null.
    @Column(nullable = false)
    private Integer consumerId;

    // it refers to the goods entity's id, which also can't be null.
    @Column(nullable = false)
    private Integer goodsId;

    // Ditto.
    @Column(nullable = false)
    private Integer number;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the consumerId
     */
    public Integer getConsumerId() {
        return consumerId;
    }

    /**
     * @param consumerId the consumerId to set
     */
    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * @return the goodsId
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId the goodsId to set
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

}