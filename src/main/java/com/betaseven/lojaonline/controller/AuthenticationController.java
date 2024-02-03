package com.betaseven.lojaonline.controller;

import com.betaseven.lojaonline.Exceptions.ExistingUsernameException;
import com.betaseven.lojaonline.service.impl.TokenServiceImpl;
import com.betaseven.lojaonline.domain.dtos.AuthenticationDTO;
import com.betaseven.lojaonline.domain.dtos.LoginResponseDTO;
import com.betaseven.lojaonline.domain.dtos.RegisterDTO;
import com.betaseven.lojaonline.domain.model.Usuario;
import com.betaseven.lojaonline.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    final private AuthenticationManager authenticationManager;

    final private UsuarioRepository usuarioRepository;

    final private TokenServiceImpl tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenServiceImpl tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        Authentication auth =  this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (usuarioRepository.findByUsername(registerDTO.getLogin()) != null )
            throw new ExistingUsernameException();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        Usuario novoUsuario = new Usuario(registerDTO.getLogin(), encryptedPassword, registerDTO.getRole());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();

    }
}
