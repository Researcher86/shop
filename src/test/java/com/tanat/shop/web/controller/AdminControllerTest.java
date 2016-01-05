package com.tanat.shop.web.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

/**
 * Тестируем контроллер админки
 * Created by Tanat on 10.12.2015.
 */
public class AdminControllerTest extends AbstractControllerTest {

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/index"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void testLogin() throws Exception {
//        mockMvc = webAppContextSetup(this.wac).apply(springSecurity()).build();
//        mockMvc.perform(get("/admin/login")
//              .
//        );
    }

    @Test
    public void testIncorrectLogin() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {

    }
}