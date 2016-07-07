package com.tanat.shop.web.controller;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер главной страницы магазина
 * Created by Tanat on 16.11.2015.
 */
@Controller
public class IndexController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    private static final String PAGE_INDEX = "index";
    private static final String VIEW_FOLDER = "index";
    private static final String GOODS_LIST = "goodsList";
    private static final String PAGE_ABOUT_COMPANY = "aboutCompany";
    private static final String PAGE_SHIPPING = "shipping";
    private static final String PAGE_CONTACTS = "contacts";
    private static final String PAGE_PRICE_LIST = "priceList";
    private static final int PAGE_SIZE = 5;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    public IndexController() {
        super(VIEW_FOLDER);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testUtf8() {
        return "Русский тест";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LOG.debug("Render index page");

        Page<Goods> page = goodsService.getGoodsLog(1, PAGE_SIZE);
        addPagination(model, page);

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@RequestParam String str, Model model) {
        LOG.debug("Render result page, search goods by {}", str);

        model.addAttribute(GOODS_LIST, goodsService.findByName(str));

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String pageGoods(@PathVariable Integer pageNumber, Model model) {
        LOG.debug("Page goods");

        Page<Goods> page = goodsService.getGoodsLog(pageNumber, PAGE_SIZE);
        addPagination(model, page);

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable Long id, Model model) {
        LOG.debug("Render page goods by category {}", id);

        Page<Goods> page = goodsService.getGoodsLogByCategory(1, PAGE_SIZE, id);
        addPagination(model, page);

        return getView(model, PAGE_INDEX);
    }

    @RequestMapping(value = "/categories/{id}/pages/{pageNumber}", method = RequestMethod.GET)
    public String pageCategoryGoods(@PathVariable Long id, @PathVariable Integer pageNumber, Model model) {
        LOG.debug("Page category goods");

        Page<Goods> page = goodsService.getGoodsLogByCategory(pageNumber, PAGE_SIZE, id);
        addPagination(model, page);

        return getView(model, PAGE_INDEX);
    }

    private void addPagination(Model model, Page<Goods> page) {
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 5, page.getTotalPages());

        model.addAttribute("goodsLog", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        model.addAttribute(GOODS_LIST, page.getContent());
    }

    @RequestMapping(value = "/aboutCompany", method = RequestMethod.GET)
    public String aboutCompany(Model model) {
        LOG.debug("Render page \"About Company\"");

        return getView(model, PAGE_ABOUT_COMPANY);
    }

    @RequestMapping(value = "/shipping", method = RequestMethod.GET)
    public String shipping(Model model) {
        LOG.debug("Render page shipping");

        return getView(model, PAGE_SHIPPING);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(Model model) {
        LOG.info("Render page contacts");

        return getView(model, PAGE_CONTACTS);
    }

    @RequestMapping(value = "/priceList", method = RequestMethod.GET)
    public String priceList(Model model) {
        LOG.debug("Render page price list");

        return getView(model, PAGE_PRICE_LIST);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public String showGoods(@PathVariable Long id, Model model) {
        LOG.debug("Render page goods {}", id);

        model.addAttribute("goods", goodsService.getByIdAndAllComments(id));
        model.addAttribute("comment", new Comment());

        return getView(model, "showGoods");
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.POST)
    public String addCommentForGoods(@PathVariable Long goodsId, @Validated Comment comment, BindingResult bindingResult, Model model) {
        LOG.debug("Add comment for goods {}", goodsId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("goods", goodsService.getByIdAndAllComments(goodsId));
            model.addAttribute("comment", comment);
            return getView(model, "showGoods");
        }

        goodsService.addCommentForGoods(comment, goodsId);

        return "redirect:/goods/" + goodsId;
    }

}
