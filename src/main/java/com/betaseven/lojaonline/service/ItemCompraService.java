package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.ItemDTO;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;

import java.util.List;

public interface ItemCompraService {
    void salvarItensCompra(List<ItemDTO> itens, Compra compra) throws Exception;

    List<ItemCompra> buscarItensDaCompra(Compra compra);
}
