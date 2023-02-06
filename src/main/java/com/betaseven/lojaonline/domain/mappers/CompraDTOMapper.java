package com.betaseven.lojaonline.domain.mappers;

import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.domain.model.Compra;
import org.springframework.stereotype.Component;

@Component
public interface CompraDTOMapper {
    CompraDTO execute(Compra compra);
}
