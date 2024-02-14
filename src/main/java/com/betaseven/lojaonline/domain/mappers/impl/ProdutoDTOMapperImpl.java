package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.domain.mappers.ProdutoDTOMapper;
import com.betaseven.lojaonline.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTOMapperImpl implements ProdutoDTOMapper {
    @Override
    public ProdutoDTO execute(Produto produto) {
        return ProdutoDTO.builder()
                .idProduto(produto.getIdProduto())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .build();
    }
}
