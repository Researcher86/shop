package com.tanat.shop.web.controller;

import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер главной страницы магазина
 * Created by Tanat on 16.11.2015.
 */
@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("main index");

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goodsList", goodsService.getAll());

        model.addAttribute("content", "index");
        return "/main/template";
    }

}
