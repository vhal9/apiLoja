package com.betaseven.lojaonline.Exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Usuario nao tem acesso ao recurso solicitado");
    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
