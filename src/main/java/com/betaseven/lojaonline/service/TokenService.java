package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.model.Usuario;

public interface TokenService {
    String generateToken(Usuario usuario);
    String getSubjectFromToken(String token);
    Usuario getUsuarioFromToken(String token);
    Usuario getUsuarioFromSession();

}
