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

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getTotalPrice() {
        return goods.getPrice() * goodsCount;
    }

}
