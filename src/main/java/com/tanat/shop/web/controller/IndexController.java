package com.tanat.shop.web.controller;

import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер главной страницы магазина
 * Created by Tanat on 16.11.2015.
 */
@Controller
public class IndexController extends AbstractController {
    public static final String PAGE_INDEX = "index";
    public static final String VIEW_FOLDER = "index";
    public static final String CATEGORIES = "categories";
    public static final String GOODS_LIST = "goodsList";
    public static final String PAGE_ABOUT_COMPANY = "aboutCompany";
    public static final String PAGE_SHIPPING = "shipping";
    public static final String PAGE_CONTACTS = "contacts";

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public IndexController() {
        super(VIEW_FOLDER);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("goods index");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.getAll());

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@RequestParam String str, Model model) {
        logger.info("goods search");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.findByName(str));

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable Long id, Model model) {
        logger.info("category show");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.findByCategoryId(id));

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/aboutCompany", method = RequestMethod.GET)
    public String aboutCompany(Model model) {
        logger.info("Show info \"About Company\"");

        return getView(model, PAGE_ABOUT_COMPANY);
    }

    @RequestMapping(value = "/shipping", method = RequestMethod.GET)
    public String shipping(Model model) {
        logger.info("Show info shipping");

        return getView(model, PAGE_SHIPPING);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(Model model) {
        logger.info("Show info contacts");

        return getView(model, PAGE_CONTACTS);
    }

}
