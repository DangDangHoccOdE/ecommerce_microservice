package com.microservice.userservice.exception.wrapper;

public class UserObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserObjectNotFoundException() {
        super();
    }

    public UserObjectNotFoundException(String message) {
        super(message);
    }

    public UserObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
