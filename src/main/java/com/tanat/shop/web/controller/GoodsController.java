package com.tanat.shop.web.controller;

import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер главной страницы магазина
 * Created by Tanat on 16.11.2015.
 */
@Controller
public class GoodsController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public GoodsController() {
        super("index");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("goods index");

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goodsList", goodsService.getAll());

        return getView(model, "index");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@RequestParam String str, Model model) {
        logger.info("goods search");

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goodsList", goodsService.findByName(str));

        return getView(model, "index");
    }

}
