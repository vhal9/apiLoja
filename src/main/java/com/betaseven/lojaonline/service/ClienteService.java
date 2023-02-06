package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.model.Cliente;

public interface ClienteService {
    Cliente buscarCliente(Long idCliente) throws Exception;
}
