package com.tanat.shop.domain.model;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Order {
    private Goods goods;
    private int goodsCount;

    public Order() {
    }

    public Order(Goods goods, int goodsCount) {
        this.goods = goods;
        this.goodsCount = goodsCount;
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
}
