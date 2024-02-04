package com.betaseven.lojaonline.Exceptions;

public class ExistingUsernameException extends RuntimeException {
    public ExistingUsernameException() {
        super("Username ja existente");
    }
}
