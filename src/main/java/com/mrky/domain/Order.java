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

    public Order() {

    }

    public Order(Integer id, Integer number, Consumer consumer, Goods goods) {
        this.id = id;
        this.number = number;
        this.consumer = consumer;
        this.goods = goods;
    }

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