package com.tanat.shop.service;

import com.tanat.shop.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanat on 30.09.2015.
 */
public class ShopService {

    public Cart newCart() {
        return new Cart();
    }

    public CategoryOfGoods newCategoryOfGoods(String name) {
        return new CategoryOfGoods(name);
    }
    
    public Comment newComment(String text, Client client) {
        return new Comment(text, client);
    }

    public void addCommentForGoods(Comment comment, Goods goods) {
        comment.setGoods(goods);
    }

    public void addGoodsInCategory(Goods goods, CategoryOfGoods categoryOfGoods) {
        categoryOfGoods.addGoods(goods);
    }

    public void cartAddGoods(Cart cart, Goods goods, int amount) {
        cart.addGoods(goods, amount);
    }
    
    
}
