package com.betaseven.lojaonline.controller;

import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;
    private final static Logger logger = LogManager.getLogger(CompraController.class);

    @GetMapping("{id}")
    public ResponseEntity<CompraDTO> buscarCompra(@PathVariable(value = "id") UUID idCompra) throws Exception {
        return new ResponseEntity<>(compraService.buscarCompra(idCompra), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<CompraDTO> inserirCompra(@Valid @RequestBody CompraDTO novaCompra) {
        try {
            logger.info("Inserindo compra para o cliente " + novaCompra.getIdCompra());
            return new ResponseEntity<>(compraService.inserirCompra(novaCompra), HttpStatus.CREATED);
        } catch (Exception e ){
            logger.info("Erro executando inserirCompra: " + novaCompra);
            throw new RuntimeException("Erro executando inserirCompra: " + novaCompra);
        }

    }
}
