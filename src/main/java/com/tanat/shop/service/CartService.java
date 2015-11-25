package com.tanat.shop.service;

import com.tanat.shop.dao.CartDao;
import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanat on 25.11.2015.
 */
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

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
}
