package com.tanat.shop.service;

import com.tanat.shop.dao.CartDao;
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
    private CartDao cartDao;

    public Cart create() {
        return new Cart();
    }

    public List<Cart> getAll() {
        return cartDao.findAll();
    }

    public Cart getById(Long id) {
        return cartDao.findOne(id);
    }

    public List<Cart> getByClient(Client client) {
        return cartDao.findByClient(client);
    }

    public void addOrder(Cart cart, Goods goods, int amount) {
        cart.addOrder(goods, amount);
    }

    public void save(Cart cart) {
        cartDao.saveAndFlush(cart);
    }

    public void updateOrder(Cart cart, Long goodsId, int amount) {
        if (amount < 0) {
            throw new RuntimeException("Incorrect amount = " + amount);
        }

        Order order = cart.findOrderByGoodsId(goodsId);
        if (amount == 0) {
            cart.deleteGoods(goodsId);
        } else {
            order.setGoodsCount(amount);
        }
    }

    public void deleteGoods(Cart cart, Long goodsId) {
        cart.deleteGoods(goodsId);
    }
}
