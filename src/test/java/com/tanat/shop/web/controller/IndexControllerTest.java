package com.tanat.shop.web.controller;

import com.tanat.shop.dao.ClientDao;
import com.tanat.shop.model.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Тестируем главный контроллер магазина
 * Created by Tanat on 08.12.2015.
 */
public class IndexControllerTest extends AbstractControllerTest {

    @Autowired
    private ClientDao clientDao;

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

    @Test
    public void addCommentForGoods_textNotEmpty_authenticationClient() throws Exception {
        Client client = clientDao.saveAndFlush(Client.createSimple());

        mockMvc.perform(post("/goods/1")
                .param("text", "test")
                .sessionAttr("client", client)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("goods"))
                .andExpect(model().attribute("goods", hasProperty("comments", hasSize(1))));
    }

    @Test
    public void addCommentForGoods_textEmpty_authenticationClient() throws Exception {
        Client client = clientDao.saveAndFlush(Client.createSimple());

        mockMvc.perform(post("/goods/1")
                .param("text", "")
                .sessionAttr("client", client)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("goods"))
                .andExpect(model().attribute("text", "Text empty"))
                .andExpect(model().attribute("goods", hasProperty("comments", hasSize(0))));
    }
}
