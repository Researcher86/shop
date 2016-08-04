package com.tanat.shop.web.controller;


import com.tanat.shop.model.Client;
import com.tanat.shop.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private static final String INDEX_TEMPLATE = "/index/template";
    private static final String ERROR_ATTR = "error";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final String CLIENT = "client";
    private static final String CONTENT = "content";

    private final ClientService clientService;

    @Autowired
    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(HttpSession httpSession, Model model) {
        LOG.debug("Client login form");

        if (httpSession.getAttribute(ERROR_ATTR) != null) {
            httpSession.setAttribute(ERROR_ATTR, null);
        }

        model.addAttribute(CONTENT, "../auth/authentication.jsp");

        return INDEX_TEMPLATE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpSession httpSession, Model model) {
        LOG.debug("Client login email: {}, password: {}", email, password);

        Client storeClient = clientService.findByEmailAndPassword(email, password);

        if (storeClient != null) {
            httpSession.setAttribute(CLIENT, storeClient);
            httpSession.setAttribute(ERROR_ATTR, null);
            return REDIRECT_HOME;
        } else {
            httpSession.setAttribute(ERROR_ATTR, "Неверный логин или пароль!");
            model.addAttribute(CONTENT, "../auth/authentication.jsp");
            return INDEX_TEMPLATE;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        Client client = (Client) httpSession.getAttribute(CLIENT);

        if (client != null) {
            LOG.debug("Client logout, email: {}, password: {}", client.getEmail(), client.getPassword());
            httpSession.setAttribute(CLIENT, null);
        } else {
            LOG.warn("Client is null");
        }

        return REDIRECT_HOME;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        LOG.debug("Registration client form");

        model.addAttribute(CLIENT, new Client());
        model.addAttribute(CONTENT, "../auth/registration.jsp");

        return INDEX_TEMPLATE;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Validated Client client, BindingResult bindingResult, Model model, HttpSession httpSession) {
        LOG.debug("Registration client, fio {}, phone {}, address {}, email {}, password {}",
                client.getFio(), client.getPhone(), client.getAddress(), client.getEmail(), client.getPassword());

        if (bindingResult.hasErrors()) {
            model.addAttribute(CONTENT, "../auth/registration.jsp");
            return INDEX_TEMPLATE;
        }

        clientService.save(client);
        httpSession.setAttribute(CLIENT, client);

        return REDIRECT_HOME;
    }
}
