package com.tanat.shop.web.controller.admin;

import com.tanat.shop.model.Comment;
import com.tanat.shop.service.CommentService;
import com.tanat.shop.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String comments(Model model) {
        LOG.debug("Admin panel comment list");

        model.addAttribute("comments", service.getAll());

        return getView(model, "comments");
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public String commentFormEdit(@PathVariable Long id, Model model) {
        LOG.debug("Admin panel edit comment {}", id);

        model.addAttribute("comment", service.getById(id));

        return getView(model, "comment");
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.POST)
    public String commentEdit(@PathVariable Long id,
                              @Validated @ModelAttribute Comment comment,
                              BindingResult bindingResult,
                              Model model) {
        LOG.debug("Admin panel save comment {}", id);

        if (bindingResult.hasErrors()) {
            return getView(model, "comment");
        }

        Comment storeComment = service.getById(id);
        storeComment.setText(comment.getText());
        storeComment.setActive(comment.isActive());

        service.save(storeComment);

        return "redirect:/admin/comments";
    }

}
