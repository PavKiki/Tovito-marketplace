package com.tovito.backend.exception;

public class EmailAlreadyRegistered extends Exception {
    public EmailAlreadyRegistered(String message) {
        super(message);
    }
}
