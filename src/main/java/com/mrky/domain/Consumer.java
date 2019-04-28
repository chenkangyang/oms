package com.mrky.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consumer_id")
    private Integer id;

    @Column(name = "consumer_password", nullable = false)
    private String password;

    @Column(name = "consumer_address")
    private String address;

    // 用于多个表之间的关系表示，一个consumer可以对应多个order
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumer")
    private Collection<Order> orders = new HashSet<Order>();

    public Consumer() {

    }

    public Consumer(Integer id, String password, String address) {
        this.id = id;
        this.password = password;
        this.address = address;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the orders
     */
    public Collection<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

}