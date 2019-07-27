package com.apside.prono.errors;

public class PronosException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PronosException(String message) {
        super(message);
    }
}
