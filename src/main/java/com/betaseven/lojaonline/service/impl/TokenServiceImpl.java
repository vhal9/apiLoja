package com.betaseven.lojaonline.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.betaseven.lojaonline.domain.model.Usuario;
import com.betaseven.lojaonline.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.security.token.secret:jwt}")
    private String secret;

    @Value("${api.security.session.time:60}")
    private Long sessionTime;

    final private String EMISSOR = "auth-api";

    @Override
    public String generateToken(Usuario usuario) {
        try {
            Usuario userLogin = new Usuario();
            userLogin.setId(usuario.getId());
            userLogin.setUsername(usuario.getUsername());
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(EMISSOR)
                    .withSubject(userLogin.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao executar generateToken - usuario: " + usuario.getUsername(), e);
        }
    }

    @Override
    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(EMISSOR)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    @Override
    public Usuario getUsuarioFromSession() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioLogado;
    }

    private Instant genExpirationDate () {
        return LocalDateTime.now().plusHours(sessionTime).toInstant(ZoneOffset.of("-03:00"));
    }
}
