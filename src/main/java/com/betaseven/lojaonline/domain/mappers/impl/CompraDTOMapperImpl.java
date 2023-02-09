package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.domain.mappers.CompraDTOMapper;
import com.betaseven.lojaonline.domain.model.Compra;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CompraDTOMapperImpl implements CompraDTOMapper {

    @Override
    public CompraDTO execute(Compra compra) {
        return CompraDTO.builder()
                .idCompra(compra.getIdCompra())
                .idCliente(compra.getCliente().getIdCliente())
                .dataCompra(compra.getDataCompra())
                .statusCompra(compra.getStatusCompra())
                .dataInicioProcessamento(compra.getDataInicioProcessamento())
                .dataFimProcessamento(compra.getDataFimProcessamento())
                .total(compra.getTotal())
                .itens(Collections.emptyList())
                .build();
    }
}
