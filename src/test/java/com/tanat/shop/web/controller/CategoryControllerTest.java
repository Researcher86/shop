package com.tanat.shop.web.controller;

import org.junit.Ignore;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем веб контроллер категории товаров
 * Created by Tanat on 16.11.2015.
 */
@Ignore
public class CategoryControllerTest extends AbstractController {

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("/categories/list"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/categories/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("/categories/category"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testNew() throws Exception {
        mockMvc.perform(get("/categories/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/categories/edit"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testEdit() throws Exception {
        mockMvc.perform(get("/categories/edit/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("/categories/edit"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testDeleteNotGoods() throws Exception {
        mockMvc.perform(get("/categories/delete/1"))
                .andExpect(view().name("/errors/error"));
    }

    @Test
    public void testDeleteWithGoods() throws Exception {
        mockMvc.perform(get("/categories/delete/2"))
                .andExpect(view().name("/errors/error"));
    }

    @Test
    public void testDeleteNotExists() throws Exception {
        mockMvc.perform(get("/categories/delete/30"))
                .andExpect(view().name("/errors/error"));
    }

    @Test
    public void testGetByIdNotExists() throws Exception {
        mockMvc.perform(get("/categories/30"))
                .andExpect(view().name("/errors/error"));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/categories/save")
                .param("id", "2")
                .param("name", "Test")
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories"));
    }
}