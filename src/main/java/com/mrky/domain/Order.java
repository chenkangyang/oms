package com.mrky.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @Column(name = "order_id") // 订单ID
    private Integer id;

    // it refers to the consumer entity's id, which can't be null.
    @Column(name = "order_consumerId", nullable = false) // 订单的顾客id
    private Integer consumerId;

    // it refers to the goods entity's id, which also can't be null.
    @Column(name = "order_goodsId", nullable = false) // 订单的商品id
    private Integer goodsId;

    // Ditto.
    @Column(name = "order_number", nullable = false) // 订单购买的数目
    private Integer number;

    // 一群order对应一个consumer
    @ManyToOne(targetEntity = Consumer.class)
    @JoinColumn(name = "order_consumerId", referencedColumnName = "consumer_id")
    private Consumer consumer;

    // 一个order对应以一个goods
    @OneToOne(mappedBy = "order", targetEntity = Goods.class)
    @JoinColumn(name = "order_goodsId", referencedColumnName = "goods_id")
    private Goods goods;

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

    /**
     * @return the consumer
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * @param consumer the consumer to set
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    /**
     * @return the goods
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * @param goods the goods to set
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

}