package com.tanat.shop.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public Comment() {
        this.date = Calendar.getInstance();
    }

    public Comment(String text, Client client) {
        this();
        this.text = text;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
