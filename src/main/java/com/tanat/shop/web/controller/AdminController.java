package com.tanat.shop.web.controller;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

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
            LOG.error("Error delete category", e);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

            return new ResponseEntity<>(e.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goodsList(Model model) {
        LOG.debug("Admin panel goods list");

        model.addAttribute("goodsList", goodsService.getAll());

        return getView(model, "goodsList");
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public String goodsEdit(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel goods {}", id);

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goods", goodsService.getById(id));

        return getView(model, "goods");
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.POST)
    public String goodsSave(@PathVariable Long id,
                            @RequestParam MultipartFile file,
                            @Validated @ModelAttribute Goods goods,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes) throws IOException {
        LOG.debug("Admin panel goods {} save", id);

        Goods storeGoods = goodsService.getById(id);


        if (bindingResult.hasErrors()) {
            goods.setImage(storeGoods.getImage());
            model.addAttribute("categories", categoryService.getAll());
            return getView(model, "goods");
        }

        if (!file.isEmpty()) {
            storeGoods.setImage(new Image(file.getBytes(), file.getContentType()));
        }
        storeGoods.setName(goods.getName());
        storeGoods.setDescription(goods.getDescription());
        storeGoods.setPrice(goods.getPrice());
        storeGoods.setCategory(goods.getCategory());

        goodsService.save(storeGoods);

        return "redirect:/admin/goods";
    }
}
