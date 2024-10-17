package org.dsc.locadora.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(ErroMessage erroMessage) {
        super(erroMessage.getMessage());
    }
}
