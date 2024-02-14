package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.domain.mappers.ProdutoMapper;
import com.betaseven.lojaonline.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {
    @Override
    public Produto execute(ProdutoDTO produto) {
        return new Produto(produto.getIdProduto(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}
