package com.tanat.shop.web.controller.admin;

import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class GoodsController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public GoodsController() {
        super("admin");
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goodsList(Model model) {
        LOG.debug("Admin panel goods list");

        model.addAttribute("goodsList", goodsService.getAll());

        return getView(model, "goodsList");
    }

    @RequestMapping(value = "/goods/create", method = RequestMethod.GET)
    public String goodsFromCreate(Model model) {
        LOG.debug("Admin panel form create goods");

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goods", new Goods());

        return getView(model, "goods");
    }

    @RequestMapping(value = "/goods/create", method = RequestMethod.POST)
    public String goodsCreate(@RequestParam MultipartFile file,
                            @Validated @ModelAttribute Goods goods,
                            BindingResult bindingResult, Model model) throws IOException {
        LOG.debug("Admin panel create goods");

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return getView(model, "goods");
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

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("goods", goodsService.getById(id));

        return getView(model, "goods");
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
