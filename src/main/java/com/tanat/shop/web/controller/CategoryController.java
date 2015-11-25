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

/**
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("categories");

        model.addAttribute("categories", categoryService.getAll());

        return "/categories/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Long id, Model model) {
        logger.info("getById {}", id);

        model.addAttribute("category", categoryService.getById(id));

        return "/categories/category";
    }
}