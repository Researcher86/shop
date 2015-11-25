package com.tanat.shop.web.controller;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import com.tanat.shop.web.utils.CategoryEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("goodsAll");

        model.addAttribute("goodsAll", goodsService.getAll());

        return "/goods/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Long id, Model model) {
        logger.info("getById {}", id);

        model.addAttribute("goods", goodsService.getById(id));

        return "/goods/goods";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute Goods goods, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "/goods/edit";
    }
    // TODO: Надо объеденить эти два метода
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit2(@PathVariable Long id, Model model) {
        model.addAttribute("goods", goodsService.getById(id));
        model.addAttribute("categories", categoryService.getAll());
        return "/goods/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String save(@ModelAttribute Goods goods /*, BindingResult bindingResult, Model model*/) {

        goodsService.save(goods);
        return "redirect:/goods";
    }

    @Autowired
    private CategoryEditor categoryEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, this.categoryEditor);
    }
}
