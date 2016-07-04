package com.tanat.shop.service;

import com.tanat.shop.repository.CartRepository;
import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart create() {
        return new Cart();
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart getById(Long id) {
        return cartRepository.findOne(id);
    }

    public List<Cart> getByClient(Client client) {
        return cartRepository.findByClient(client);
    }

    public void addOrder(Cart cart, Goods goods, int amount) {
        cart.addOrder(goods, amount);
    }

    public void save(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    public void updateOrder(Cart cart, Long goodsId, int amount) {
        if (amount < 0) {
            throw new AppException("Incorrect amount = " + amount);
        }

        if (amount == 0) {
            cart.deleteGoods(goodsId);
        } else {
            Order order = cart.findOrderByGoodsId(goodsId);
            order.setAmount(amount);
        }
    }

    public void deleteGoods(Cart cart, Long goodsId) {
        cart.deleteGoods(goodsId);
    }
}
