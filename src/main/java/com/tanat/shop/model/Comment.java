package com.tanat.shop.model;

import java.util.Calendar;

/**
 * Created by Tanat on 02.10.2015.
 */
public class Comment {
    private int id;
    private String text;
    private Calendar date;
    private Client client;

    public Comment() {
    }

    public Comment(String text, Client client) {
        this.text = text;
        this.client = client;
        this.date = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
