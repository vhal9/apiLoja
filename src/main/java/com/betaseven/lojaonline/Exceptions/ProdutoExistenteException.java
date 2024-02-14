package com.betaseven.lojaonline.Exceptions;

public class ProdutoExistenteException extends RuntimeException{
    public ProdutoExistenteException() {
        super("Produto ja existente");
    }
}
