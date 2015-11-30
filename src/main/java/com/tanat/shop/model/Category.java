package com.tanat.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public void addGoods(Goods goods) {
        if (goodsList == null) {
            goodsList = new ArrayList<>();
        }
        goods.setCategory(this);
        goodsList.add(goods);
    }

}
