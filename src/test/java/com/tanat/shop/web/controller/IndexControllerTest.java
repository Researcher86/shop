package com.tanat.shop.web.controller;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import com.tanat.shop.service.ClientService;
import com.tanat.shop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем главный контроллер магазина
 * Created by Tanat on 08.12.2015.
 */
public class IndexControllerTest extends AbstractControllerTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private GoodsService goodsService;

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
        Client client = clientService.save(Client.createSimple());

        mockMvc.perform(post("/goods/1")
                .param("text", "test")
                .param("client.id", String.valueOf(client.getId()))
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/goods/1"));

        Goods goods = goodsService.getById(1L);
        assertEquals(1, goods.getComments().size());
    }

    @Test
    public void addCommentForGoods_textEmpty_authenticationClient() throws Exception {
        Client client = clientService.save(Client.createSimple());

        mockMvc.perform(post("/goods/1")
                .param("text", "")
                .param("client.id", String.valueOf(client.getId()))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("goods"))
                .andExpect(model().attributeExists("comment"))
                .andExpect(model().attributeHasErrors("comment"))
                .andExpect(model().attribute("goods", hasProperty("comments", hasSize(0))));
    }

    @Test
    public void getGoodsPageByCategory() throws Exception {
        mockMvc.perform(get("/categories/1/pages/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/template"))
                .andExpect(model().attributeExists("content"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("goodsList", hasSize(1)));
    }
}
