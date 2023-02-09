package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.domain.dtos.ItemDTO;
import com.betaseven.lojaonline.domain.mappers.ItemCompraMapper;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;
import com.betaseven.lojaonline.repositories.ItemCompraRepository;
import com.betaseven.lojaonline.service.ItemCompraService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCompraServiceImpl implements ItemCompraService {

    private final ItemCompraRepository itemCompraRepository;
    private final ItemCompraMapper itemCompraMapper;
    private final Logger logger = LogManager.getLogger(ItemCompraServiceImpl.class);

    @Override
    public void salvarItensCompra(List<ItemDTO> itens, Compra compra) throws Exception {
        try {
            List<ItemCompra> itensCompra = new ArrayList<>();
            for (ItemDTO itemDTO : itens) {
                itensCompra.add(itemCompraMapper.execute(itemDTO, compra));
            }
            itemCompraRepository.saveAll(itensCompra);
        }
        catch (Exception e) {
            logger.error("Nao foi possivel salvar os itens da compra");
            throw new Exception("Nao foi possivel salvar os itens da compra");
        }

    }

    @Override
    public List<ItemCompra> buscarItensDaCompra(Compra compra) {
        return itemCompraRepository.findAllByCompra(compra);
    }
}
