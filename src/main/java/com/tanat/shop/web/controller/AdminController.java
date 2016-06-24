package com.tanat.shop.web.controller;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {

    private static Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public AdminController() {
        super("admin");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        LOG.debug("Render login page");

        ModelAndView model = new ModelAndView("admin/login");
        if (error != null) {
            LOG.debug("Invalid username or password!");
            model.addObject("error", "Неверный логин или пароль!");
        }

        if (logout != null) {
            LOG.debug("User logout");
            model.addObject("msg", "Вы успешно вышли из системы.");
        }

        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Admin panel render index page");


        return getView(model, "index");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categoriesList(Model model) {
        LOG.debug("Admin panel render categories list page");

        model.addAttribute("categories", categoryService.getAll());

        return getView(model, "categories");
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String categoryForm(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel render show category {} page", id);

        model.addAttribute("category", categoryService.getById(id));

        return getView(model, "category");
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
    public String categorySave(@PathVariable Long id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        LOG.debug("Admin panel render show category {} page", id);

        if (!"".equals(name.trim())) {
            Category category = categoryService.getById(id);
            category.setName(name);
            categoryService.save(category);
        } else {
            redirectAttributes.addFlashAttribute("error", "Вы указали некорректное имя категории");
        }


        return "redirect:/admin/categories/" + id;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        LOG.debug("Admin panel delete category {}", id);

        try {
            categoryService.delete(id);
        } catch (AppException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "text/html; charset=utf-8");

            return new ResponseEntity<>(e.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
