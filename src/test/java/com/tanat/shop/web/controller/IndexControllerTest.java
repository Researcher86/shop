package com.tanat.shop.web.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Тестируем главный контроллер магазина
 * Created by Tanat on 08.12.2015.
 */
public class IndexControllerTest extends AbstractControllerTest {

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("goodsList"));
    }

    @Test
    public void showGoods() throws Exception {
        mockMvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("goods"));
    }
}
