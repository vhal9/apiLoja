package com.betaseven.lojaonline.domain.mappers.impl;

import com.betaseven.lojaonline.domain.dtos.ItemDTO;
import com.betaseven.lojaonline.domain.mappers.ItemCompraMapper;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;
import com.betaseven.lojaonline.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemCompraMapperImpl implements ItemCompraMapper {

    @Override
    public ItemCompra execute(ItemDTO itemDTO, Compra compra) {
        return new ItemCompra(null, compra, new Produto(itemDTO.idProduto), itemDTO.getQuantidade());
    }
}
