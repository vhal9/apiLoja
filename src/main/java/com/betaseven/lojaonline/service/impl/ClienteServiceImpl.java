package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.Exceptions.ClienteExistenteException;
import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.domain.mappers.ClienteDTOMapper;
import com.betaseven.lojaonline.domain.mappers.ClienteMapper;
import com.betaseven.lojaonline.domain.model.Cliente;
import com.betaseven.lojaonline.repositories.ClienteRepository;
import com.betaseven.lojaonline.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LogManager.getLogger(ClienteServiceImpl.class);
    private final ClienteRepository clienteRepository;
    private final ClienteDTOMapper clienteDTOMapper;
    private final ClienteMapper clienteMapper;

    @Override
    public Optional<Cliente> buscarCliente(UUID idCliente) {
        try {
            return clienteRepository.findById(idCliente);
        } catch (Exception e) {
            logger.error("Erro executando buscarCliente: " + idCliente, e);
            throw e;
        }
    }

    @Override
    public ClienteDTO inserirCliente(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = validarCliente(clienteDTO);
            Cliente clienteSalvo = clienteRepository.save(cliente);
            return clienteDTOMapper.execute(clienteSalvo);
        } catch (Exception e) {
            logger.error("Erro executando inserirCliente: cliente: " + clienteDTO, e);
            throw e;
        }
    }

    private Cliente validarCliente(ClienteDTO clienteDTO) {
        List<Cliente> clientes = clienteRepository.findClienteByCnpjOrRazaoSocial(clienteDTO.getCnpj(), clienteDTO.getRazaoSocial());
        if (!clientes.isEmpty()) {
            List<String> listCamposRepetidos = new ArrayList<>();
            if (clientes.stream().anyMatch(c -> (clienteDTO.getCnpj().equals(c.getCnpj()))))
                listCamposRepetidos.add("CNPJ");
            if (clientes.stream().anyMatch(c -> clienteDTO.getRazaoSocial().equals(c.getRazaoSocial())))
                listCamposRepetidos.add("Razao Social");
            String camposRepetidos = "";
            if (listCamposRepetidos.size() > 1) {
                camposRepetidos = listCamposRepetidos.get(0) + ", " + listCamposRepetidos.get(1);
            } else if (listCamposRepetidos.size() == 1) {
                camposRepetidos = listCamposRepetidos.get(0);
            }
            throw new ClienteExistenteException(String.format("Ja existem clientes com essas informacoes: [%s]", camposRepetidos));
        }
        Cliente novoCliente = clienteMapper.execute(clienteDTO);
        novoCliente.setDataCriacao(LocalDate.now());
        novoCliente.setFlAtivo(true);
        return novoCliente;
    }
}
