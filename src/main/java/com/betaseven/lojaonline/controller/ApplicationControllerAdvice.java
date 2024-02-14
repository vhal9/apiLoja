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
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .messages(erros)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(CompraNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> compraNotFoundExceptionHandler(CompraNotFoundException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.NOT_FOUND)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<RestErrorMessageDTO> authenticationExceptionHandler(AuthenticationException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.NOT_FOUND)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(DisabledException.class)
    private ResponseEntity<RestErrorMessageDTO> disabledExceptionHandler(DisabledException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestErrorMessageDTO> badCredentialsExceptionHandler() {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of("Credenciais Invalidas"))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }
    @ExceptionHandler(ExistingUsernameException.class)
    private ResponseEntity<RestErrorMessageDTO> existingUsernameExceptionHandler(ExistingUsernameException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    private ResponseEntity<RestErrorMessageDTO> clienteNotFoundExceptionHandler(ClienteNotFoundException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(ClienteExistenteException.class)
    private ResponseEntity<RestErrorMessageDTO> clienteExistenteExceptionHandler(ClienteExistenteException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<RestErrorMessageDTO> unauthorizedExceptionHandler(ClienteNotFoundException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessageDTO> runtimeExceptionHanlder(RuntimeException e) {
        RestErrorMessageDTO restErrorMessageDTO = RestErrorMessageDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .messages(List.of(e.getMessage()))
                .build();
        return ResponseEntity.status(restErrorMessageDTO.getStatus()).body(restErrorMessageDTO);
    }
}
