package org.dsc.locadora.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(ErroMessage erroMessage) {
        super(erroMessage.getMessage());
    }
}
