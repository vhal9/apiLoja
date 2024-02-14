package com.betaseven.lojaonline.controller;

import com.betaseven.lojaonline.Exceptions.*;
import com.betaseven.lojaonline.domain.dtos.erros.RestErrorMessageDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErrorMessageDTO> methodArgumentNotValidExceptionHlander(MethodArgumentNotValidException e) {
        List<String> erros = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return handlerBadRequest(erros);
    }

    @ExceptionHandler(CompraNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> compraNotFoundExceptionHandler(CompraNotFoundException e) {
        return handlerNotFound(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<RestErrorMessageDTO> authenticationExceptionHandler(AuthenticationException e) {
        return handlerNotFound(e.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    private ResponseEntity<RestErrorMessageDTO> disabledExceptionHandler(DisabledException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestErrorMessageDTO> badCredentialsExceptionHandler() {
        return handlerBadRequest("Credenciais Invalidas");
    }
    @ExceptionHandler(ExistingUsernameException.class)
    private ResponseEntity<RestErrorMessageDTO> existingUsernameExceptionHandler(ExistingUsernameException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> clienteNotFoundExceptionHandler(ClienteNotFoundException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(ClienteExistenteException.class)
    private ResponseEntity<RestErrorMessageDTO> clienteExistenteExceptionHandler(ClienteExistenteException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(ProdutoExistenteException.class)
    private ResponseEntity<RestErrorMessageDTO> produtoExistenteExceptionHandler(ProdutoExistenteException e) {
        return handlerBadRequest(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<RestErrorMessageDTO> unauthorizedExceptionHandler(ClienteNotFoundException e) {
        return handlerUnauthorized(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessageDTO> runtimeExceptionHanlder(RuntimeException e) {
        return handlerInternalServerError(e.getMessage());
    }

    private ResponseEntity<RestErrorMessageDTO> handlerBadRequest(String mensagem) {
        return handlerExceptionsGenericas(List.of(mensagem), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RestErrorMessageDTO> handlerBadRequest(List<String> mensagens) {
        return handlerExceptionsGenericas(mensagens, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RestErrorMessageDTO> handlerNotFound(String mensagem) {
        return handlerExceptionsGenericas(List.of(mensagem), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<RestErrorMessageDTO> handlerUnauthorized(String mensagem) {
        return handlerExceptionsGenericas(List.of(mensagem), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<RestErrorMessageDTO> handlerInternalServerError(String mensagem) {
        return handlerExceptionsGenericas(List.of(mensagem), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<RestErrorMessageDTO> handlerExceptionsGenericas(List<String> mensagens, HttpStatus status) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(status)
                .messages(mensagens)
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }
}
