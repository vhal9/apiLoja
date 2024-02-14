package com.betaseven.lojaonline.controller;

import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {
    private final static Logger logger = LogManager.getLogger(CompraController.class);

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> buscarProdutos() {
        return new ResponseEntity<>(produtoService.buscarTodosProdutos(), HttpStatus.OK);
    }

    @GetMapping("/getProdutosPorNome/{nomeProduto}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutosPorNome(@PathVariable(value = "nomeProduto") String nomeProduto) {
        return new ResponseEntity<>(produtoService.buscarProdutosPorNome(nomeProduto), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insereProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            return new ResponseEntity<>(produtoService.insereProduto(produtoDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao executar insereProduto: " + produtoDTO, e);
            throw e;
        }
    }
}
