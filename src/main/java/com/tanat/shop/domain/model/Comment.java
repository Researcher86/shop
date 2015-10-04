package com.tanat.shop.domain.model;

import java.util.Calendar;

/**
 * Created by Tanat on 02.10.2015.
 */
public class Comment {
    private String text;
    private Calendar date;
    private Goods goods;
    private Client client;

    public Comment() {
    }

    public Comment(String text, Goods goods, Client client) {
        this.text = text;
        this.goods = goods;
        this.client = client;
        this.date = Calendar.getInstance();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
