package com.betaseven.lojaonline.domain.mappers;

import com.betaseven.lojaonline.domain.dtos.ItemDTO;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;
import org.springframework.stereotype.Component;

@Component
public interface ItemCompraMapper {
    ItemCompra execute(ItemDTO itemDTO, Compra compra);
}
