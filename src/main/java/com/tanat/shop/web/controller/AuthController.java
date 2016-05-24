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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        LOG.debug("Client login email: {}, password: {}", email, password);

        Client storeClient = clientService.findByEmailAndPassword(email, password);

        if (storeClient != null) {
            request.getSession().setAttribute("client", storeClient);
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


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        LOG.debug("Registration client form");

        model.addAttribute("client", new Client());
        model.addAttribute("content", "../auth/registration.jsp");

        return "/index/template";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Validated Client client, BindingResult bindingResult, Model model) {
        LOG.debug("Registration client, fio {}, phone {}, address {}, email {}, password {}",
                client.getFio(), client.getPhone(), client.getAddress(), client.getEmail(), client.getPassword());

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "../auth/registration.jsp");
            return "/index/template";
        }

        clientService.save(client);

        return "redirect:/";
    }
}
