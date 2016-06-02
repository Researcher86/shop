package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
import com.tanat.shop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем контроллер корзины
 * Created by Comp on 30.05.2016.
 */
public class CartControllerTest extends AbstractControllerTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(request().sessionAttribute("cart", notNullValue()));
    }

    @Test
    public void updateQuality() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 2);
        cart.addOrder(goodsService.getById(2L), 1);

        mockMvc.perform(put("/cart/goods")
                .contentType("application/x-www-form-urlencoded")
                .content("goodsId=1&quality=10")
                .sessionAttr("cart", cart)
        )
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(2))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(95))));
    }

    @Test
    public void updateIncorrectQuality() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 2);
        cart.addOrder(goodsService.getById(2L), 1);

        mockMvc.perform(put("/cart/goods")
                 .contentType("application/x-www-form-urlencoded")
                 .content("goodsId=1&quality=-1")
                .sessionAttr("cart", cart)
        )
                .andExpect(status().isBadRequest())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(2))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(55))));
    }

    @Test
    public void deleteOrder() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 2);
        cart.addOrder(goodsService.getById(2L), 1);

        mockMvc.perform(delete("/cart/goods/1")
                .sessionAttr("cart", cart)
        )
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(1))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(45))));
    }

}