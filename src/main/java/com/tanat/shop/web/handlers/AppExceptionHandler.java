package com.tanat.shop.web.handlers;

import com.tanat.shop.exceptions.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Глобальный контроллер для обработки ошибок приложения
 * Created by Tanat on 27.11.2015.
 */
@ControllerAdvice
public class AppExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(AppException.class);

    // Total control - setup a model and return the view name yourself. Or consider
    // subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", exception.getClass().getCanonicalName());
        mav.addObject("exception", exception);
        mav.addObject("msg", "Неизвестная ошибка!");
        mav.setViewName("/errors/error");
        return mav;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleErrorNotFound(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", exception.getClass().getCanonicalName());
        mav.addObject("exception", exception);
        mav.addObject("msg", exception.getMessage());
        mav.setViewName("/errors/error");
        return mav;
    }
}
