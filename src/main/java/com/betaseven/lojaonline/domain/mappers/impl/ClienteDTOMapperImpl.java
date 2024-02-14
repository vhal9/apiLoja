package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.mappers.ClienteDTOMapper;
import com.betaseven.lojaonline.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteDTOMapperImpl implements ClienteDTOMapper {
    @Override
    public ClienteDTO execute(Cliente cliente) {
        return ClienteDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nomeFantasia(cliente.getNomeFantasia())
                .razaoSocial(cliente.getRazaoSocial())
                .cnpj(cliente.getCnpj())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .dataCriacao(cliente.getDataCriacao())
                .flAtivo(cliente.getFlAtivo())
                .build();
    }
}
