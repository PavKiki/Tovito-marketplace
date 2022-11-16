package com.tovito.backend.exception;

public class WrongPassword extends Exception{
    public WrongPassword(String message) {
        super(message);
    }
}
