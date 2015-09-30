package com.tanat.shop.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class CategoryOfGoods {
    private String name;
    private List<Goods> goods;

    public CategoryOfGoods() {
        this.goods = new ArrayList<>();
    }

    public CategoryOfGoods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void addGoods(Goods goods) {
        this.goods.add(goods);
        goods.getCategoryOfGoods().getGoods().remove(goods);
        goods.setCategoryOfGoods(this);
    }
}
