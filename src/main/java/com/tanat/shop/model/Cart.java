package com.tanat.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Cart extends AbstractModel {
    private Client client;
    private String shippingAddress;
    private List<Order> orders = new ArrayList<>();

    public Cart() {
    }

    public Cart(Client client, String shippingAddress) {
        this.client = client;
        this.shippingAddress = shippingAddress;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void addGoods(Goods goods, int amount) {
        this.orders.add(new Order(goods, amount));
    }

    public int getTotalPrice() {
        int result = 0;

        for (Order order : orders) {
            result += order.getTotalPrice();
        }

        return result;
    }
}
