package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.domain.model.Compra;

import java.util.List;

public interface CompraService {

    CompraDTO inserirCompra(CompraDTO compraDTO) throws Exception;

    CompraDTO buscarCompra(Long idCompra) throws Exception;

    List<Compra> buscarComprasPendentes();

    void iniciarProcessamentoDaCompra(Long idCompra);

    void finalizarProcessamentoDaCompra(Long idCompra);
    void finalizarProcessamentoDaCompraNegada(Long idCompra);

    void finalizarProcessamentoDaCompraComErro(Long idCompra);
}
