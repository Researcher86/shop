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
    private int amount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Order() {
    }

    public Order(Goods goods, int amount, Cart cart) {
        this.goods = goods;
        this.amount = amount;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public Goods getGoods() {
        return goods;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getTotalPrice() {
        return goods.getPrice() * amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

}
