package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> optionalUserDetails =  usuarioRepository.findByUsername(username);
        if (optionalUserDetails.isEmpty())
            throw new UsernameNotFoundException("Credenciais Invalidas");
        return optionalUserDetails.get();
    }
}
