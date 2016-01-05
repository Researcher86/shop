package com.tanat.shop.exception;

/**
 * Exception бросается при возникновении ошибки в приложении
 * Created by Tanat on 25.11.2015.
 */
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
