package com.mrky.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer consumerId;

    @Column(nullable = false)
    private String consumerName;

    @Column(nullable = false)
    private String consumerPassword;

    @Column(nullable = false)
    private String consumerAddress;

    @Column(nullable = false)
    private int consumerAmount;

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
     * @return the consumerPassword
     */
    public String getConsumerPassword() {
        return consumerPassword;
    }

    /**
     * @param consumerPassword the consumerPassword to set
     */
    public void setConsumerPassword(String consumerPassword) {
        this.consumerPassword = consumerPassword;
    }

    /**
     * @return the consumerAddress
     */
    public String getConsumerAddress() {
        return consumerAddress;
    }

    /**
     * @param consumerAddress the consumerAddress to set
     */
    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }

    /**
     * @return the consumerAmount
     */
    public int getConsumerAmount() {
        return consumerAmount;
    }

    /**
     * @param consumerAmount the consumerAmount to set
     */
    public void setConsumerAmount(int consumerAmount) {
        this.consumerAmount = consumerAmount;
    }

    // 用于多个表之间的关系表示，一个consumer可以对应多个order
    // 删除用户时会删除所有有关其订单
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumer", cascade =
    // CascadeType.ALL)
    // private Collection<Order> orders = new HashSet<Order>();

}