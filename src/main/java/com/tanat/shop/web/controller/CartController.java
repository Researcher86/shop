package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Order;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Render index page");

        if(!model.containsAttribute("cart")) {
            Cart cart = new Cart();

            cart.addOrder(goodsService.getById(1L), 2);
            cart.addOrder(goodsService.getById(2L), 1);

            model.addAttribute("cart", cart);
        }

        model.addAttribute("content", "../cart/index.jsp");

        return "index/template";
    }
}
