package com.tanat.shop.web.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(view().name("/goods/list"))
                .andExpect(model().attributeExists("goodsAll"));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goods/goods"))
                .andExpect(model().attributeExists("goods"));
    }

    @Test
    public void testAdd() throws Exception {
        mockMvc.perform(get("/goods/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goods/edit"))
                .andExpect(model().attributeExists("goods"));
    }

    @Test
    public void testEdit() throws Exception {
        mockMvc.perform(get("/goods/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/goods/edit"))
                .andExpect(model().attributeExists("goods"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/goods/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/goods"));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/goods/save")
                .param("id", "1")
                .param("name", "Test")
                .param("price", "56")
                .param("description", "Test test")
                .param("category.id", "2")
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/goods"));
    }
}