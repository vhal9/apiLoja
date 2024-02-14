package com.betaseven.lojaonline.Exceptions;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException(String mensagem){
        super(mensagem);
    }
}
