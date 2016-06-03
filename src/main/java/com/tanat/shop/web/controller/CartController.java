package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public Cart getCart() {
        LOG.debug("Create Cart");
        return cartService.create();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Render cart index page");

        model.addAttribute("content", "../cart/index.jsp");

        return "index/template";
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public ResponseEntity<String> addOrder(@RequestParam Long goodsId, @RequestParam int amount, @ModelAttribute Cart cart) {
        LOG.debug("Add goods = {}, amount = {} to cart", goodsId, amount);

        Goods goods = goodsService.getById(goodsId);

        try {
            cartService.addOrder(cart, goods, amount);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/goods", method = RequestMethod.PUT)
    public ResponseEntity<String> updateOrder(@RequestBody MultiValueMap<String, String> body, @ModelAttribute Cart cart) {
        LOG.debug("Update order");

        Long goodsId = Long.parseLong(body.getFirst("goodsId"));
        int amount = Integer.parseInt(body.getFirst("amount"));

        try {
            cartService.updateOrder(cart, goodsId, amount);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrder(@PathVariable Long goodsId, @ModelAttribute Cart cart) {
        LOG.debug("Delete goods = {}", goodsId);

        cartService.deleteGoods(cart, goodsId);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
