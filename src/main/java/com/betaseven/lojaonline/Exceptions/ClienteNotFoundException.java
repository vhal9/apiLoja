package com.betaseven.lojaonline.Exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException() {
        super("Cliente nao encontrado");
    }
}
