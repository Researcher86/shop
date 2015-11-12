package com.tanat.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoodsList() {
        return Collections.unmodifiableList(goodsList);
    }

    public void addGoods(Goods goods) {
        if (goodsList == null) {
            goodsList = new ArrayList<>();
        }
        goodsList.add(goods);
    }

}
