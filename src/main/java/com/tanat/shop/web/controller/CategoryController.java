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

@Controller
@RequestMapping(value = "/categories")
public class CategoryController extends AbstractController{

    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
        super("index");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable Long id, Model model) {
        logger.info("category show");

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goodsList", goodsService.findByCategoryId(id));

        return getView(model, "index");
    }
}

