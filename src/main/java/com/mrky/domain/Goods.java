package com.mrky.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer goodsId;

    @Column(nullable = false)
    private String goodsName;

    @Column(nullable = false)
    private int goodsPrice;

    @Column(nullable = false)
    private Integer goodsStock;

    // 放弃使用关系映射注解
    @Column(nullable = false)
    private Integer merchantId;

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
     * @return the goodsPrice
     */
    public int getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * @param goodsPrice the goodsPrice to set
     */
    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * @return the goodsStock
     */
    public Integer getGoodsStock() {
        return goodsStock;
    }

    /**
     * @param goodsStock the goodsStock to set
     */
    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

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

    // 暂时不做
    // @Column(name = "goods_imgUrl")
    // private String imgUrl;

    // 很多的goods对应一个merchant
    // 表示merchant不能为空，删除商品不影响merchant
    // @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional =
    // false)
    // @JoinColumn(name = "goods_goods_id", referencedColumnName = "merchant_id")
    // private Merchant merchant;

    // // 一个goods可以在多个order中出现
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
    // @JoinColumn(name = "order_order_id", referencedColumnName = "goods_id")
    // private Collection<Order> order = new HashSet<Order>();

}