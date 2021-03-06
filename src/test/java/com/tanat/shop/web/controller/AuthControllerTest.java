package com.tanat.shop.web.controller;

import com.tanat.shop.model.Client;
import com.tanat.shop.service.ClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем контроллер авторизации
 * Created by Tanat on 08.12.2015.
 */
public class AuthControllerTest extends AbstractControllerTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void testLoginForm() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andDo(print())
                .andExpect(request().sessionAttribute("client", nullValue()))
                .andExpect(request().sessionAttribute("error", nullValue()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("content"))
                .andExpect(view().name("/index/template"));
    }

    @Test
    public void testCorrectLoginData() throws Exception {
        clientService.save(Client.createSimple());

        mockMvc.perform(post("/auth/login")
                .param("email", "researcher86@mail.ru")
                .param("password", "123456")
        )
                .andDo(print())
                .andExpect(request().sessionAttribute("client", notNullValue()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testIncorrectLoginData() throws Exception {
        mockMvc.perform(post("/auth/login")
                .param("email", "test")
                .param("password", "test")
        )
                .andDo(print())
                .andExpect(request().sessionAttribute("client", nullValue()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("content"))
                .andExpect(view().name("/index/template"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/auth/logout")
                .sessionAttr("client", new Client("test", "test", "test", "test@gmail.com", "test"))
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRegistrationClientForm() throws Exception {
        mockMvc.perform(get("/auth/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("content", "../auth/registration.jsp"))
                .andExpect(view().name("/index/template"));
    }

    @Test
    public void testRegistrationCorrectClientData() throws Exception {
        mockMvc.perform(post("/auth/registration")
                .param("fio", "Альпенов Танат Маратович")
                .param("phone", "123456654321")
                .param("address", "Kostanay")
                .param("email", "test@test.ru")
                .param("password", "123")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRegistrationIncorrectClientData() throws Exception {
        mockMvc.perform(post("/auth/registration")
                .param("fio", "")
                .param("phone", "")
                .param("address", "")
                .param("email", "")
                .param("password", "")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("content", "../auth/registration.jsp"))
                .andExpect(view().name("/index/template"));
    }
}
