package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.mappers.ClienteMapper;
import com.betaseven.lojaonline.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente execute(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getIdCliente(), clienteDTO.getNomeFantasia(), clienteDTO.getRazaoSocial(), clienteDTO.getCnpj(),
                clienteDTO.getEmail(), clienteDTO.getTelefone(), clienteDTO.getDataCriacao(), clienteDTO.getFlAtivo());
    }
}
