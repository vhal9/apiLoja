package com.betaseven.lojaonline.domain.mappers;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface ClienteMapper {
    Cliente execute(ClienteDTO clienteDTO);
}
