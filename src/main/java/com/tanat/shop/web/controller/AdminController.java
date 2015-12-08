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

/**
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public AdminController() {
        super("admin");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("categories");

        model.addAttribute("categories", categoryService.getAll());

        return getView(model, "index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/admin/login";
    }

}
