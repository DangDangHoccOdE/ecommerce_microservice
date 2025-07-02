package com.microservice.userservice.exception.wrapper;

public class CredentialNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CredentialNotFoundException() {
        super();
    }

    public CredentialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialNotFoundException(Throwable cause) {
        super(cause);
    }

    public CredentialNotFoundException(String message) {
        super(message);
    }
}
