package com.aaron.springbootDemo.exception;

public class SpringBootDemoException extends Exception {

    public SpringBootDemoException() {
        super();
    }

    public SpringBootDemoException(String message) {
        super(message);
    }

    public SpringBootDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringBootDemoException(Throwable cause) {
        super(cause);
    }

    protected SpringBootDemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
