package com.tanat.shop.web.controller.admin;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import com.tanat.shop.web.controller.AbstractController;
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

import java.io.IOException;
import java.util.List;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class GoodsController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
    private static final String GOODS_LIST = "goodsList";
    private static final String GOODS = "goods";

    private final GoodsService goodsService;

    private final CategoryService categoryService;

    @Autowired
    public GoodsController(CategoryService categoryService, GoodsService goodsService) {
        super("admin");
        this.categoryService = categoryService;
        this.goodsService = goodsService;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goodsList(Model model) {
        LOG.debug("Admin panel goods list");

        model.addAttribute(GOODS_LIST, goodsService.getAll());

        return getView(model, GOODS_LIST);
    }

    @RequestMapping(value = "/goods/create", method = RequestMethod.GET)
    public String goodsFromCreate(Model model) {
        LOG.debug("Admin panel form create goods");

        model.addAttribute(GOODS, new Goods());

        return getView(model, GOODS);
    }

    @RequestMapping(value = "/goods/create", method = RequestMethod.POST)
    public String goodsCreate(@RequestParam MultipartFile file,
                            @Validated @ModelAttribute Goods goods,
                            BindingResult bindingResult, Model model) throws IOException {
        LOG.debug("Admin panel create goods");

        if (bindingResult.hasErrors()) {
            return getView(model, GOODS);
        }

        if (!file.isEmpty()) {
            goods.setImage(new Image(file.getBytes(), file.getContentType()));
        }

        goodsService.save(goods);

        return "redirect:/admin/goods";
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public String goodsFromEdit(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel from edit goods {}", id);

        model.addAttribute(GOODS, goodsService.getById(id));

        return getView(model, GOODS);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.POST)
    public String goodsEdit(@PathVariable Long id,
                            @RequestParam MultipartFile file,
                            @Validated @ModelAttribute Goods goods,
                            BindingResult bindingResult, Model model) throws IOException {
        LOG.debug("Admin panel goods {} edit", id);

        Goods storeGoods = goodsService.getById(id);

        if (bindingResult.hasErrors()) {
            goods.setImage(storeGoods.getImage());
            return getView(model, GOODS);
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

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> goodsDelete(@PathVariable Long id) {
        LOG.debug("Admin panel delete goods {}", id);

        try {
            goodsService.delete(id);
        } catch (AppException e) {
            LOG.error("Error delete goods", e);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

            return new ResponseEntity<>(e.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
