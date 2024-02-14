package com.betaseven.lojaonline.controller;

import com.betaseven.lojaonline.domain.dtos.ClienteDTO;
import com.betaseven.lojaonline.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final static Logger logger = LogManager.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> inserirCliente(@Valid @RequestBody ClienteDTO novoCliente) {
        try {
            logger.info("Inserindo Cliente: " + novoCliente);
            return new ResponseEntity<>(clienteService.inserirCliente(novoCliente), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro executando inserirCliente: " + novoCliente, e);
            throw e;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable(value = "id") UUID idCliente) {
        return new ResponseEntity<>(clienteService.buscarClienteDTO(idCliente), HttpStatus.OK);
    }

    @GetMapping("/nomeFantasia/{nomeFantasia}")
    public ResponseEntity<List<ClienteDTO>> buscarClientesPorNomeFantasia(@PathVariable(value = "nomeFantasia") String nomeFantasia) {
        return new ResponseEntity<>(clienteService.buscarClientesDTOPorNomeFantasia(nomeFantasia), HttpStatus.OK);
    }
}
