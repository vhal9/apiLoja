package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.model.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Cliente buscarCliente(UUID idCliente);
    ClienteDTO buscarClienteDTO(UUID uuid);
    List<ClienteDTO> buscarClientesDTOPorNomeFantasia(String nomeFantasia);
    ClienteDTO inserirCliente(ClienteDTO clienteDTO);
}
