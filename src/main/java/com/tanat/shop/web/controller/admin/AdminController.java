package com.tanat.shop.web.controller.admin;

import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public AdminController() {
        super("admin");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Admin panel render index page");

        return getView(model, "index");
    }

}
