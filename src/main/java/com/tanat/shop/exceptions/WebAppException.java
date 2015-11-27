package com.tanat.shop.exceptions;

/**
 * Created by Tanat on 25.11.2015.
 */
public class WebAppException extends RuntimeException {
    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

}
