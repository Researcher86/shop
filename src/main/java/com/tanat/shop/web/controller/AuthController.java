package com.tanat.shop.web.controller;


import com.tanat.shop.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Client client, HttpServletRequest request) {
        LOG.debug("Client login email: {}, password: {}", client.getEmail(), client.getPassword());

        if ("researcher2286@gmail.com".equals(client.getEmail())) {
            request.getSession().setAttribute("client", client);
            request.getSession().setAttribute("error", null);
        } else {
            request.getSession().setAttribute("error", "Incorrect email or password");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");

        if (client != null) {
            LOG.debug("Client logout, email: {}, password: {}", client.getEmail(), client.getPassword());
            request.getSession().setAttribute("client", null);
        } else {
            LOG.debug("Client not login", client.getEmail(), client.getPassword());
        }

        return "redirect:/";
    }
}
