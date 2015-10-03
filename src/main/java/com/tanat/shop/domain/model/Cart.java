package com.tanat.shop.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Cart {
    private List<Order> orders = new ArrayList<>();

    public Cart() {
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
