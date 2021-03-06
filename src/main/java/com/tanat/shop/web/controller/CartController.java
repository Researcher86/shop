package com.tanat.shop.web.controller;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Client;
import com.tanat.shop.model.Goods;
import com.tanat.shop.service.CartService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки запросов к корзине
 * Created by Comp on 30.05.2016.
 */
@Controller
@RequestMapping(value = "/cart")
@SessionAttributes({"cart", "client"})
public class CartController {
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);
    private static final String INDEX_TEMPLATE = "index/template";
    private static final String CONTENT = "content";

    private final GoodsService goodsService;

    private final CartService cartService;

    @Autowired
    public CartController(GoodsService goodsService, CartService cartService) {
        this.goodsService = goodsService;
        this.cartService = cartService;
    }

    @ModelAttribute
    public Cart getCart() {
        LOG.debug("Create Cart");
        return cartService.create();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Render cart index page");

        model.addAttribute(CONTENT, "../cart/index.jsp");

        return INDEX_TEMPLATE;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public ResponseEntity<String> addOrder(@RequestParam Long goodsId, @RequestParam int amount, @ModelAttribute Cart cart) {
        LOG.debug("Add goods = {}, amount = {} to cart", goodsId, amount);

        Goods goods = goodsService.getById(goodsId);

        try {
            cartService.addOrder(cart, goods, amount);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AppException e) {
            LOG.error("Error add order to cart", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/goods", method = RequestMethod.PUT)
    public ResponseEntity<String> updateOrder(@RequestBody MultiValueMap<String, String> body, @ModelAttribute Cart cart) {
        LOG.debug("Update order");

        Long goodsId = Long.valueOf(body.getFirst("goodsId"));
        int amount = Integer.parseInt(body.getFirst("amount"));

        try {
            cartService.updateOrder(cart, goodsId, amount);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AppException e) {
            LOG.error("Error update order to cart", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrder(@PathVariable Long goodsId, @ModelAttribute Cart cart) {
        LOG.debug("Delete goods = {}", goodsId);

        cartService.deleteGoods(cart, goodsId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkoutInfo(Model model) {
        model.addAttribute(CONTENT, "../cart/checkout.jsp");
        return INDEX_TEMPLATE;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutSave(@ModelAttribute Client client, @Validated @ModelAttribute Cart cart, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(CONTENT, "../cart/checkout.jsp");
        } else {
            cart.setClient(client);

            cartService.save(cart);

            model.addAttribute("cart", new Cart());
            model.addAttribute("orderNumber", cart.getId());
            model.addAttribute(CONTENT, "../cart/checkoutSuccess.jsp");
        }
        return INDEX_TEMPLATE;
    }
}
