package org.dsc.locadora.exception;

public enum ErroMessage {

    CLIENTE_NOT_FOUND("Cliente não encontrado.");

    private String message;

    ErroMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
