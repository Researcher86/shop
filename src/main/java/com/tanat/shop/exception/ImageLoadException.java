package com.tanat.shop.exception;

public class ImageLoadException extends AppException {
    public ImageLoadException(String message) {
        super(message);
    }

    public ImageLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
