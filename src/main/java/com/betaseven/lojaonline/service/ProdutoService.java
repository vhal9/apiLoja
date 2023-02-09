package com.betaseven.lojaonline.service;


import com.betaseven.lojaonline.domain.model.Produto;

public interface ProdutoService {
    void subtrairEstoque(Produto produto, Long quantidades);
}
