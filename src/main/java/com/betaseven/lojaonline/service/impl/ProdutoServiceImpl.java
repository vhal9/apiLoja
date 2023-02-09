package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.domain.model.Produto;
import com.betaseven.lojaonline.repositories.ProdutoRepository;
import com.betaseven.lojaonline.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final Logger logger = LogManager.getLogger(ProdutoServiceImpl.class);

    @Override
    @Transactional
    public void subtrairEstoque(Produto produto, Long quantidade) {
        logger.info("Atualizando o estoque do produto " + produto);
        Long novaQuantidade = produto.getQuantidade() - quantidade;
        produtoRepository.atualizarEstoque(produto.getIdProduto(), novaQuantidade);
    }

}
