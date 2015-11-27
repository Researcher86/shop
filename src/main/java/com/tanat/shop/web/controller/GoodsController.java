package com.tanat.shop.web.controller;

import com.tanat.shop.model.Goods;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
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
@RequestMapping(value = "/goods")
public class GoodsController extends BaseController {

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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String add(@ModelAttribute Goods goods, Model model) {
        logger.info("new goods");
        model.addAttribute("categories", categoryService.getAll());
        return "/goods/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        logger.info("edit goods {}", id);
        model.addAttribute("goods", goodsService.getById(id));
        model.addAttribute("categories", categoryService.getAll());

        return "/goods/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        logger.info("delete goods {}", id);
        goodsService.delete(id);

        return "redirect:/goods";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Goods goods) {
        logger.info("save goods [{}, {}, {}, {}]", goods.getName(), goods.getPrice(), goods.getDescription(), goods.getCategory().getId());
        goodsService.save(goods);

        return "redirect:/goods";
    }
}
