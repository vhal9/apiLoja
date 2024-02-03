package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.domain.model.Cliente;
import com.betaseven.lojaonline.repositories.ClienteRepository;
import com.betaseven.lojaonline.service.ClienteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private static Logger logger = LogManager.getLogger(ClienteServiceImpl.class);

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> buscarCliente(UUID idCliente) {
        try {
            return clienteRepository.findById(idCliente);
        } catch (Exception e) {
            logger.error("Erro executando buscarCliente: " + idCliente, e);
            throw e;
        }
    }
}
