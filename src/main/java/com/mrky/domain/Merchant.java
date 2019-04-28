package com.mrky.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "merchant_id")
    private Integer id;

    @Column(name = "merchant_password")
    private String password;

    @Column(name = "merchant_income")
    private Integer income;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
    private Collection<Goods> goods = new HashSet<Goods>();

    public Merchant() {

    }

    public Merchant(Integer id, String password, Integer income) {
        this.id = id;
        this.password = password;
        this.income = income;
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
     * @return the income
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * @return the goods
     */
    public Collection<Goods> getGoods() {
        return goods;
    }

    /**
     * @param goods the goods to set
     */
    public void setGoods(Collection<Goods> goods) {
        this.goods = goods;
    }

}