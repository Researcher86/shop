package com.tanat.shop.web.controller;

import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер главной страницы магазина
 * Created by Tanat on 16.11.2015.
 */
@Controller
public class IndexController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    private static final String PAGE_INDEX = "index";
    private static final String VIEW_FOLDER = "index";
    private static final String CATEGORIES = "categories";
    private static final String GOODS_LIST = "goodsList";
    private static final String PAGE_ABOUT_COMPANY = "aboutCompany";
    private static final String PAGE_SHIPPING = "shipping";
    private static final String PAGE_CONTACTS = "contacts";
    private static final String PAGE_PRICE_LIST = "priceList";

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public IndexController() {
        super(VIEW_FOLDER);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LOG.info("goods index");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.getAll());

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@RequestParam String str, Model model) {
        LOG.info("goods search");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.findByName(str));

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String showCategory(@ModelAttribute Category category, Model model) {
        LOG.info("category show");

        model.addAttribute(CATEGORIES, categoryService.getAll());
        model.addAttribute(GOODS_LIST, goodsService.findByCategory(category));

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/aboutCompany", method = RequestMethod.GET)
    public String aboutCompany(Model model) {
        LOG.info("Show info \"About Company\"");

        return getView(model, PAGE_ABOUT_COMPANY);
    }

    @RequestMapping(value = "/shipping", method = RequestMethod.GET)
    public String shipping(Model model) {
        LOG.info("Show info shipping");

        return getView(model, PAGE_SHIPPING);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(Model model) {
        LOG.info("Show info contacts");

        return getView(model, PAGE_CONTACTS);
    }

    @RequestMapping(value = "/priceList", method = RequestMethod.GET)
    public String priceList(Model model) {
        LOG.info("Show info price list");

        return getView(model, PAGE_PRICE_LIST);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public String showGoods(@PathVariable Long id, Model model) {
        LOG.debug("Show goods {}", id);

        model.addAttribute("goods", goodsService.getByIdAndAllComments(id));

        return getView(model, "showGoods");
    }

}
