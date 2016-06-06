package com.tanat.shop.model;

import com.tanat.shop.exception.AppException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<Order> orders = new ArrayList<>();

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
        if (goods == null) {
            throw new AppException("Goods is null");
        }

        if (amount <= 0) {
            throw new AppException("Incorrect amount");
        }

        Optional<Order> order = findOrderByGoods(goods.getId());

        if (order.isPresent()) {
            Order temp = order.get();
            temp.addAmount(amount);
        } else {
            orders.add(new Order(goods, amount, this));
        }
    }

    public int getTotalPrice() {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }

    public int getTotalAmount() {
        return orders.stream().mapToInt(Order::getAmount).sum();
    }

    public void deleteGoods(Long goodsId) {
        orders = orders.stream().filter(o -> o.getGoods().getId() != goodsId).collect(Collectors.toList());
    }

    private Optional<Order> findOrderByGoods(Long goodsId) {
        return orders.stream().filter(o -> o.getGoods().getId() == goodsId).findFirst();
    }

    public Order findOrderByGoodsId(Long goodsId) {

        Optional<Order> order = findOrderByGoods(goodsId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new AppException("Order contain goods " + goodsId + " not found");
        }
    }
}
