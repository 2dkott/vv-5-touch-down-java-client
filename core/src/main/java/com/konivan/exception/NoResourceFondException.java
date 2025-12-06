package com.konivan.exception;

public class NoResourceFondException extends Exception {
    public NoResourceFondException(String resourceName) {
        super(String.format("There is no entity with name %s in scene during load", resourceName));
    }
}
