package com.tanat.shop.exceptions;

/**
 * Created by Tanat on 27.11.2015.
 */
public class NotFound extends WebAppException {
    public NotFound(String message) {
        super(message);
    }

    public NotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
