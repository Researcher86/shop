package com.tanat.shop.model;

import com.tanat.shop.exception.AppException;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.*;
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

    @NotBlank(message = "Не заполнено поле адрес доставки")
    private String shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cart")
    private List<Order> orders = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderDate;

    private boolean processed;

    public Cart() {
    }

    public Cart(Client client) {
        this();
        this.client = client;
    }

    public Long getId() {
        return id;
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
        orders = orders.stream().filter(o -> !Objects.equals(o.getGoods().getId(), goodsId)).collect(Collectors.toList());
    }

    private Optional<Order> findOrderByGoods(Long goodsId) {
        return orders.stream().filter(o -> Objects.equals(o.getGoods().getId(), goodsId)).findFirst();
    }

    public Order findOrderByGoodsId(Long goodsId) {

        Optional<Order> order = findOrderByGoods(goodsId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new AppException("Order contain goods " + goodsId + " not found");
        }
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    @PrePersist
    public void initOrderDate() {
        if (!processed) {
            orderDate = Calendar.getInstance();
        }
    }

    public boolean isProcessed() {
        return processed;
    }

    public void processed() {
        this.processed = true;
    }
}
