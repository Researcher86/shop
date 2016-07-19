package com.tanat.shop.web.controller.admin;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.web.controller.AbstractController;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class CategoryController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    public CategoryController() {
        super("admin");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categoriesList(Model model) {
        LOG.debug("Admin panel categories list");

        model.addAttribute("categories", service.getAll());

        return getView(model, "categories");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String categoryCreate(@RequestParam String name, RedirectAttributes redirectAttributes) {
        LOG.debug("Admin panel create category");

        if ("".equals(name.trim())) {
            redirectAttributes.addFlashAttribute("error", "Вы указали некорректное имя категории");
        } else {
            service.save(new Category(name.trim()));
        }

        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String categoryFormEdit(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel form edit category {} page", id);

        model.addAttribute("category", service.getById(id));

        return getView(model, "category");
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
    public String categoryEdit(@PathVariable Long id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        LOG.debug("Admin panel render show category {} page", id);

        if (!"".equals(name.trim())) {
            Category category = service.getById(id);
            category.setName(name.trim());
            service.save(category);
        } else {
            redirectAttributes.addFlashAttribute("error", "Вы указали некорректное имя категории");
        }

        return "redirect:/admin/categories/" + id;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> categoryDelete(@PathVariable Long id) {
        LOG.debug("Admin panel delete category {}", id);

        try {
            service.delete(id);
        } catch (AppException e) {
            LOG.error("Error delete category", e);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

            return new ResponseEntity<>(e.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
