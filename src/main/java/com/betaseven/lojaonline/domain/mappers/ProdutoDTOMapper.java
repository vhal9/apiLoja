package com.betaseven.lojaonline.domain.mappers;

import com.betaseven.lojaonline.domain.dtos.ProdutoDTO;
import com.betaseven.lojaonline.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public interface ProdutoDTOMapper {
    ProdutoDTO execute(Produto produto);
}
