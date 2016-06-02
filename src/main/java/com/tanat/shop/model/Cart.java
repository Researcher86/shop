package com.tanat.shop.model;

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
        Optional<Order> order = findOrderByGoods(goods.getId());

        if (order.isPresent()) {
            Order temp = order.get();
            temp.setGoodsCount(temp.getGoodsCount() + amount);
        } else {
            orders.add(new Order(goods, amount, this));
        }
    }

    public int getTotalPrice() {
        int result = 0;

        for (Order order : orders) {
            result += order.getTotalPrice();
        }

        return result;
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
            throw new RuntimeException("Order contain goods " + goodsId + " not found");
        }
    }

    public boolean goodsExists(Long goodsId) {
        return findOrderByGoods(goodsId).isPresent();
    }
}
