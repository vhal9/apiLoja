package com.betaseven.lojaonline.service;

import com.betaseven.lojaonline.domain.dtos.CompraDTO;

public interface CompraService {

    CompraDTO inserirCompra(CompraDTO compraDTO) throws Exception;

    CompraDTO buscarCompra(Long idCompra) throws Exception;
}
