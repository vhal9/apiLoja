package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.domain.model.Produto;

import java.util.List;

public interface ProdutoService {
    void subtrairEstoque(Produto produto, Long quantidades);
    List<ProdutoDTO> buscarTodosProdutos();
    ProdutoDTO insereProduto(ProdutoDTO produtoDTO);
    List<ProdutoDTO> buscarProdutosPorNome(String nomeProduto);
}
