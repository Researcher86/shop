package com.tanat.shop.model;

import javax.persistence.*;

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

    public Goods getGoods() {
        return goods;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public int getTotalPrice() {
        return goods.getPrice() * goodsCount;
    }

}
