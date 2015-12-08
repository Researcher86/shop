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
public class MainControllerTest extends AbstractController {

    @Test
    public void testIndexAction() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
//                .andExpect(view().name("/categories/list"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("goodsList"));
    }
}
