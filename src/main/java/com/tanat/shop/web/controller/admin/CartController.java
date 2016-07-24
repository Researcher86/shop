package com.tanat.shop.web.controller.admin;

import com.tanat.shop.model.Cart;
import com.tanat.shop.service.CartService;
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
@Controller(value = "AdminCartController")
@RequestMapping(value = "/admin")
public class CartController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService service;

    public CartController() {
        super("admin");
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public String carts(Model model) {
        LOG.debug("Admin panel carts list");

        model.addAttribute("carts", service.getAll());

        return getView(model, "carts");
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.GET)
    public String cart(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel show cart {}", id);

        model.addAttribute("cart", service.getById(id));

        return getView(model, "cart");
    }

    @RequestMapping(value = "/carts/{id}/processed", method = RequestMethod.POST)
    public String processedCart(@PathVariable Long id) {
        LOG.debug("Admin panel processed cart {}", id);

        Cart cart = service.getById(id);

        cart.processed();

        service.save(cart);

        return "redirect:/admin/carts";
    }

}
