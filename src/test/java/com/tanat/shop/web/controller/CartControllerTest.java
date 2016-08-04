package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.service.ClientService;
import com.tanat.shop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

/**
 * Тестируем контроллер корзины
 * Created by Comp on 30.05.2016.
 */
public class CartControllerTest extends AbstractControllerTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ClientService clientService;

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/cart"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(request().sessionAttribute("cart", notNullValue()));
    }

    @Test
    public void updateAmount() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 1);

        mockMvc.perform(put("/cart/goods")
                .contentType("application/x-www-form-urlencoded")
                .content("goodsId=1&amount=10")
                .sessionAttr("cart", cart)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(1))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(849000))));
    }

    @Test
    public void updateIncorrectAmount() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 1);

        mockMvc.perform(put("/cart/goods")
                .contentType("application/x-www-form-urlencoded")
                .content("goodsId=1&amount=-1")
                .sessionAttr("cart", cart)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(1))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(84900))));
    }

    @Test
    public void deleteOrder() throws Exception {
        Cart cart = new Cart();
        cart.addOrder(goodsService.getById(1L), 2);
        cart.addOrder(goodsService.getById(2L), 1);

        mockMvc.perform(delete("/cart/goods/1")
                .sessionAttr("cart", cart)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(1))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(84900))));
    }

    @Test
    public void addGoodsInCart() throws Exception {
        mockMvc.perform(post("/cart/goods")
                .param("goodsId", "3")
                .param("amount", "1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(1))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(99000))));
    }

    @Test
    public void addIncorrectGoodsInCart() throws Exception {
        mockMvc.perform(post("/cart/goods")
                .param("goodsId", "-3")
                .param("amount", "1")
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(0))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(0))));
    }

    @Test
    public void addIncorrectAmountInCart() throws Exception {
        mockMvc.perform(post("/cart/goods")
                .param("goodsId", "3")
                .param("amount", "-1")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(request().sessionAttribute("cart", hasProperty("orders", hasSize(0))))
                .andExpect(request().sessionAttribute("cart", hasProperty("totalPrice", is(0))));
    }

    @Test
    public void checkoutInfo() throws Exception {
        mockMvc.perform(get("/cart/checkout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("content", "../cart/checkout.jsp"))
                .andExpect(view().name("index/template"));
    }

    @Test
    public void checkoutData() throws Exception {
        Client client = Client.createSimple();
        clientService.save(client);

        mockMvc.perform(post("/cart/checkout")
                .sessionAttr("client", client)
                .sessionAttr("cart", new Cart())
                .param("shippingAddress", "test")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("content", "../cart/checkoutSuccess.jsp"))
                .andExpect(view().name("index/template"));
    }


}