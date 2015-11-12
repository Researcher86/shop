package com.tanat.shop.model;

import javax.persistence.*;

/**
 * Created by Tanat on 30.09.2015.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    private int goodsCount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Order() {
    }

    public Order(Goods goods, int goodsCount, Cart cart) {
        this.goods = goods;
        this.goodsCount = goodsCount;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getTotalPrice() {
        return goods.getPrice() * goodsCount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
