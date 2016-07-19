package com.tanat.shop.web.controller.admin;

import com.tanat.shop.service.CommentService;
import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер ртвечает за страницу админки
 * Created by Tanat on 16.11.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class CommentController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService service;

    public CommentController() {
        super("admin");
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String goodsList(Model model) {
        LOG.debug("Admin panel comment list");

        model.addAttribute("comments", service.getAll());

        return getView(model, "comments");
    }

}
