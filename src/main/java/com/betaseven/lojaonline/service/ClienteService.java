package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.model.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteService {
    Optional<Cliente> buscarCliente(UUID idCliente);
    ClienteDTO inserirCliente(ClienteDTO clienteDTO);
}
