package com.tanat.shop.web.controller.admin;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Comment;
import com.tanat.shop.service.CommentService;
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

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        super("admin");
        this.service = service;
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

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> commentDelete(@PathVariable Long id) {
        LOG.debug("Admin panel delete comment {}", id);

        try {
            service.delete(id);
        } catch (AppException e) {
            LOG.error("Error delete comment", e);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

            return new ResponseEntity<>(e.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
