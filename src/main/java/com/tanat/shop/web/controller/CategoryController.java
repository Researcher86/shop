package com.tanat.shop.web.controller;

import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String add(@ModelAttribute Category category) {
        logger.info("new category");
        return "/categories/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        logger.info("delete category {}", id);
        categoryService.delete(id);

        return "redirect:/categories";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        logger.info("edit category {}", id);

        model.addAttribute("category", categoryService.getById(id));

        return "/categories/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Category category) {
        logger.info("save category [{}, {}]", category.getId(), category.getName());

        categoryService.save(category);

        return "redirect:/categories";
    }
}
