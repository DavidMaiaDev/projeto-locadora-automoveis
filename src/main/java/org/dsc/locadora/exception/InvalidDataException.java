package org.dsc.locadora.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(ErroMessage erroMessage) {
      super(erroMessage.getMessage());
    }
}
