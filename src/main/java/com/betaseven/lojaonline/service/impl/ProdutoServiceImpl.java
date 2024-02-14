package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.Exceptions.ProdutoExistenteException;
import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.domain.mappers.ProdutoDTOMapper;
import com.betaseven.lojaonline.domain.mappers.ProdutoMapper;
import com.betaseven.lojaonline.domain.model.Produto;
import com.betaseven.lojaonline.repositories.ProdutoRepository;
import com.betaseven.lojaonline.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final Logger logger = LogManager.getLogger(ProdutoServiceImpl.class);
    private final ProdutoDTOMapper produtoDTOMapper;
    private final ProdutoMapper produtoMapper;

    @Override
    @Transactional
    public void subtrairEstoque(Produto produto, Long quantidade) {
        logger.info("Atualizando o estoque do produto " + produto);
        Long novaQuantidade = produto.getQuantidade() - quantidade;
        produtoRepository.atualizarEstoque(produto.getIdProduto(), novaQuantidade);
    }

    @Override
    public List<ProdutoDTO> buscarTodosProdutos() {
        return produtoRepository.findAll().stream().map(produtoDTOMapper::execute).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO insereProduto(ProdutoDTO produtoDTO) {
        try {
            Produto novoProduto = validarProduto(produtoDTO);
            return produtoDTOMapper.execute(produtoRepository.save(novoProduto));
        } catch (Exception e) {
            logger.error("Erro executando insereProduto: " + produtoDTO, e);
            throw e;
        }
    }

    @Override
    public List<ProdutoDTO> buscarProdutosPorNome(String nomeProduto) {
        return produtoRepository.findProdutoByNome(nomeProduto)
                .stream().map(produtoDTOMapper::execute).collect(Collectors.toList());
    }

    private Produto validarProduto(ProdutoDTO produtoDTO) {
        if (!produtoRepository.findProdutoByNome(produtoDTO.getNome()).isEmpty()) {
            throw new ProdutoExistenteException();
        }
        return produtoMapper.execute(produtoDTO);
    }
}
