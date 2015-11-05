package com.tanat.shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Goods {
    private int id;
    private String name;
    private int price;
    private String description;
    private List<Comment> comments = new ArrayList<>();

    public Goods() {
    }

    public Goods(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public void addComments(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }

        comments.add(comment);
    }
}
