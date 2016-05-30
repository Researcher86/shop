package com.tanat.shop.web.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем контроллер корзины
 * Created by Comp on 30.05.2016.
 */
public class CartControllerTest extends AbstractControllerTest {

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(request().sessionAttribute("cart", notNullValue()));
    }

}