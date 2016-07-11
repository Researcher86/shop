package com.tanat.shop.web.handler;

import com.tanat.shop.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Глобальный контроллер для обработки ошибок приложения
 * Created by Tanat on 27.11.2015.
 */
//@ControllerAdvice
public class AppExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    // Total control - setup a model and return the view name yourself. Or consider
    // subclassing ExceptionHandlerExceptionResolver (see below).
    @ExceptionHandler(Exception.class)
    public ModelAndView handle500Error(HttpServletRequest req, Exception exception) {
        logger.error("Request: {} raised {}", req.getRequestURL(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", exception.getClass().getCanonicalName());
        mav.addObject("exception", exception);
        mav.addObject("msg", "Неизвестная ошибка!");
        mav.setViewName("error/500");
        return mav;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleAppError(HttpServletRequest req, AppException exception) {
        logger.error("Request: {} raised {}", req.getRequestURL(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exceptionName", exception.getClass().getCanonicalName());
        mav.addObject("exception", exception);
        mav.addObject("msg", exception.getMessage());
        mav.setViewName("error/500");
        return mav;
    }
}
