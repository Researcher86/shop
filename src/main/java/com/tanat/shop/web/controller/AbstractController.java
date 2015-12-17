package com.tanat.shop.web.controller;

import org.springframework.ui.Model;

/**
 * Базовый контроллер
 * Содержит вспомогательные методы
 * Created by Tanat on 08.12.2015.
 */
public abstract class AbstractController {

    private final String viewFolder;

    public AbstractController(String viewFolder) {
        this.viewFolder = viewFolder;
    }

    protected String getView(Model model, String page) {
        model.addAttribute("content", page + ".jsp");
        return "/" + viewFolder + "/template";
    }
}
