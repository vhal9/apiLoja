package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.domain.model.Cliente;
import com.betaseven.lojaonline.repositories.ClienteRepository;
import com.betaseven.lojaonline.service.ClienteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private static Logger logger = LogManager.getLogger(ClienteServiceImpl.class);

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente buscarCliente(Long idCliente) throws Exception {
        logger.info("buscando cliente com id: " + idCliente);
        Optional<Cliente> optionalClient = clienteRepository.findById(idCliente);
        if (optionalClient.isEmpty()) {
            throw new Exception("Cliente nao encontrado");
        }
        return optionalClient.get();
    }
}
