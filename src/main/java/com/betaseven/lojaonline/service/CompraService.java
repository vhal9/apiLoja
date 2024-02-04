package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.domain.model.Compra;

import java.util.List;
import java.util.UUID;

public interface CompraService {
    CompraDTO inserirCompra(CompraDTO compraDTO) throws Exception;
    CompraDTO buscarCompra(UUID idCompra) throws Exception;
    List<Compra> buscarComprasPorStatus(StatusCompraEnum statusCompraEnum);
    void iniciarProcessamentoDaCompra(UUID idCompra);
    void finalizarProcessamentoDaCompra(UUID idCompra);
    void finalizarProcessamentoDaCompraNegada(UUID idCompra);
    void finalizarProcessamentoDaCompraComErro(UUID idCompra);
}
