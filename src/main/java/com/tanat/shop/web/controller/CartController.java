package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Контроллер для обработки запросов к корзине
 * Created by Comp on 30.05.2016.
 */
@Controller
@RequestMapping(value = "/cart")
@SessionAttributes({"cart"})
public class CartController {
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Render index page");

        if(!model.containsAttribute("cart")) {
            model.addAttribute("cart", new Cart());
        }

        model.addAttribute("content", "../cart/index.jsp");

        return "index/template";
    }
}
