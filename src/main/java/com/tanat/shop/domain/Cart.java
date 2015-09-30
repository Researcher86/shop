package com.tanat.shop.domain;

import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Cart {
    private Client client;
    private List<Order> orders;

    public Cart() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order orders) {
        this.orders.add(orders);
    }

    public int getTotalPrice() {
        int result = 0;

        for (Order order : orders) {
            result += order.getTotalPrice();
        }

        return result;
    }
}
