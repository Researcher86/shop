package com.tanat.shop.web.controller.admin;

import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
        super("admin");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        LOG.debug("Render login page");

        ModelAndView model = new ModelAndView("admin/login");
        if (error != null) {
            LOG.debug("Invalid username or password!");
            model.addObject("error", "Неверный логин или пароль!");
        }

        if (logout != null) {
            LOG.debug("User logout");
            model.addObject("msg", "Вы успешно вышли из системы.");
        }

        return model;
    }
}
