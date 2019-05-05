package com.mrky.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity
@Table(name = "order")
public class Order {

    // Order id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @Column(nullable = false) // 订单购买的数目
    private Integer orderNumber;

    @Column(nullable = false)
    private Integer goodsId;

    @Column(nullable = false)
    private String goodsName;

    @Column(nullable = false)
    private Integer consumerId;

    @Column(nullable = false)
    private String consumerName;

    @Column(nullable = false)
    private Integer orderAmount;

    // 1：客户下单 -> 2：客人确认收货 -> 3:客人退货 -> 4:商家同意
    @Column(nullable = false)
    private Integer orderStatus;

    // 一群order对应一个consumer
    // @ManyToOne(targetEntity = Consumer.class)
    // @JoinColumn(name = "order_order_id", referencedColumnName = "consumer_id")
    // private Consumer consumer;

    // 一群order对应以一个goods,
    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "order_order_id", referencedColumnName = "goods_id")
    // private Goods goods;

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderNumber
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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
     * @return the goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName the goodsName to set
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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
     * @return the consumerName
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * @param consumerName the consumerName to set
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderAmount
     */
    public Integer getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount the orderAmount to set
     */
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

}