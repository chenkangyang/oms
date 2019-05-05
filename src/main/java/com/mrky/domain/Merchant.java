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
    private Integer merchantId;

    @Column(nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private String merchantPassword;

    @Column(nullable = false)
    private Integer merchantAmount;

    /**
     * @return the merchantId
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return the merchantPassword
     */
    public String getMerchantPassword() {
        return merchantPassword;
    }

    /**
     * @param merchantPassword the merchantPassword to set
     */
    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    /**
     * @return the merchantAmount
     */
    public Integer getMerchantAmount() {
        return merchantAmount;
    }

    /**
     * @param merchantAmount the merchantAmount to set
     */
    public void setMerchantAmount(Integer merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    // 一个商家可以销售多间商品
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant")
    // private Collection<Goods> goods = new HashSet<Goods>();

}