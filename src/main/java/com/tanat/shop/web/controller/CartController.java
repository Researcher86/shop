package com.tanat.shop.web.controller;

import com.tanat.shop.model.Cart;
import com.tanat.shop.model.Order;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<String> update(@RequestParam Long goodsId, @RequestParam int quality, HttpSession httpSession) {
        Cart cart = (Cart) httpSession.getAttribute("cart");

        if (quality < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Order order = cart.findOrderByGoodsId(goodsId);
            if (quality == 0) {
                cart.deleteGoods(goodsId);
            } else {
                order.setGoodsCount(quality);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
