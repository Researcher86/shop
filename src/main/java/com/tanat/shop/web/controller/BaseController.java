package com.tanat.shop.web.controller;

import com.tanat.shop.exceptions.NotFound;
import com.tanat.shop.exceptions.WebAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Базовый контроллер
 * Created by Tanat on 27.11.2015.
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    // Total control - setup a model and return the view name yourself. Or consider
    // subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        return getModelAndView(req, exception, "500");
    }

    @ExceptionHandler(NotFound.class)
    public ModelAndView handleErrorNotFound(HttpServletRequest req, Exception exception) {
        return getModelAndView(req, exception, "404");
    }

    private ModelAndView getModelAndView(HttpServletRequest req, Exception exception, String code) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("/errors/" + code);
        return mav;
    }
}
