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


@RestController
@RequestMapping("compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;
    private static Logger logger = LogManager.getLogger(CompraController.class);

    @GetMapping("{id}")
    public ResponseEntity<CompraDTO> buscarCompra(@PathVariable(value = "id") Long idCompra) {
        try {
            return new ResponseEntity<>(compraService.buscarCompra(idCompra), HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<CompraDTO> inserirCompra(@Valid @RequestBody CompraDTO novaCompra) {
        try {
            logger.info("Inserindo compra para o cliente " + novaCompra.idCliente);
            return new ResponseEntity<>(compraService.inserirCompra(novaCompra), HttpStatus.CREATED);

        } catch (Exception e ){
            logger.info("Erro ao inserir compra");
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
