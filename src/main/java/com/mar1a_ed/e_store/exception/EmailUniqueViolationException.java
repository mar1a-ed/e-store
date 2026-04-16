package com.mar1a_ed.e_store.exception;

public class EmailUniqueViolationException extends RuntimeException {

    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
