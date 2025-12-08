package com.konivan.exception;

public class NoEntityFondException extends Exception {
    public NoEntityFondException(String entityName) {
        super(String.format("There is no entity with name %s in scene during load", entityName));
    }

    public NoEntityFondException(int entityId) {
        super(String.format("There is no entity with id %s in scene during load", entityId));
    }
}
