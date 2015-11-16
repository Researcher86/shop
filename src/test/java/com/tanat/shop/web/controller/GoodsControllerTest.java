package com.tanat.shop.web.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем веб контроллер товаров
 * Created by Tanat on 16.11.2015.
 */
public class GoodsControllerTest extends AbstractController {

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/goods"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goods/all"))
                .andExpect(model().attributeExists("goodsAll"));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goods/goods"))
                .andExpect(model().attributeExists("goods"));
    }
}