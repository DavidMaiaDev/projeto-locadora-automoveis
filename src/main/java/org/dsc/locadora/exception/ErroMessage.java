package org.dsc.locadora.exception;

public enum ErroMessage {

    CLIENTE_NOT_FOUND("Cliente não encontrado."),
    INVALID_CPF("O CPF digitado não é válido."),
    INVALID_EMAIL("O email digitado não é válido.");

    private String message;

    ErroMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
