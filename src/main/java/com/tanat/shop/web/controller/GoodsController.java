package com.tanat.shop.web.controller;

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
@RequestMapping(value = "/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("goodsAll");

        model.addAttribute("goodsAll", goodsService.getAll());

        return "/goods/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Long id, Model model) {
        logger.info("getById {}", id);

        model.addAttribute("goods", goodsService.getById(id));

        return "/goods/goods";
    }
}
