package com.tanat.shop.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Глобальный контроллер для обработки HTTP ошибок
 * Created by Tanat on 30.11.2015.
 */
@Controller
public class AppHttpErrorHandler {
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String handle404() {
        return "error/404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String handle500() {
        return "error/500";
    }
}
