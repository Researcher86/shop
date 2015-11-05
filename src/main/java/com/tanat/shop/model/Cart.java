package com.tanat.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Cart {
    private int id;
    private Client client;
    private String shippingAddress;
    private List<Order> orders = new ArrayList<>();

    public Cart() {
    }

    public Cart(Client client, String shippingAddress) {
        this.client = client;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void addGoods(Goods goods, int amount) {
        if (orders == null) {
            orders = new ArrayList<>();
        }

        orders.add(new Order(goods, amount));
    }

    public int getTotalPrice() {
        int result = 0;

        for (Order order : orders) {
            result += order.getTotalPrice();
        }

        return result;
    }
}
