package com.tanat.shop.web.controller.admin;

import com.tanat.shop.service.ClientService;
import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class ClientController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        super("admin");
        this.service = service;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clients(Model model) {
        LOG.debug("Admin panel clients list");

        model.addAttribute("clients", service.getAll());

        return getView(model, "clients");
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public String cart(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel show client {}", id);

        model.addAttribute("client", service.getById(id));

        return getView(model, "client");
    }
}
