package com.tanat.shop.web.controller;

import com.tanat.shop.model.Client;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем контроллер авторизации
 * Created by Tanat on 08.12.2015.
 */
public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testCorrectLoginData() throws Exception {
        mockMvc.perform(post("/auth/login")
                .param("email", "researcher2286@gmail.com")
                .param("password", "123456")
        )
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
                .andExpect(request().sessionAttribute("client", nullValue()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/auth/logout")
                .sessionAttr("client", new Client("test", "test", "test", "test@gmail.com", "test"))
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRegistrationClientForm() throws Exception {
        mockMvc.perform(get("/auth/registration"))
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
                .param("email", "test@test.com")
                .param("password", "123")
        )
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
                .andExpect(status().isOk())
                .andExpect(model().attribute("content", "../auth/registration.jsp"))
                .andExpect(view().name("/index/template"));
    }
}
