package com.tanat.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cart")
    private List<Order> orders;

    public Cart() {
    }

    public Cart(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
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

    public void addOrder(Goods goods, int amount) {
        if (orders == null) {
            orders = new ArrayList<>();
        }

        orders.add(new Order(goods, amount, this));
    }

    public int getTotalPrice() {
        int result = 0;

        for (Order order : orders) {
            result += order.getTotalPrice();
        }

        return result;
    }
}
