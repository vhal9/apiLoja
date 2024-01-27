package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;
import com.betaseven.lojaonline.service.CompraService;
import com.betaseven.lojaonline.service.ItemCompraService;
import com.betaseven.lojaonline.service.ProcessamentoDeCompraService;
import com.betaseven.lojaonline.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessamentoDeComprasPendentesServiceImpl implements ProcessamentoDeCompraService {

    private final CompraService compraService;
    private final ItemCompraService itemCompraService;
    private final ProdutoService produtoService;
    private final Logger logger = LogManager.getLogger(ProcessamentoDeComprasPendentesServiceImpl.class);

    @Override
    public void process() {
        //buscar compras pendentes
        logger.info("Buscando compras pendentes");
        List<Compra> comprasPendentes = compraService.buscarComprasPorStatus(StatusCompraEnum.PENDENTE);

        comprasPendentes.forEach(this::processarCompra);

    }

    private void processarCompra(Compra compra) {
        try {
            logger.info("Começando o processamento da compra " + compra);
            compraService.iniciarProcessamentoDaCompra(compra.getIdCompra());
            List<ItemCompra> itens = itemCompraService.buscarItensDaCompra(compra);

            if (podeRealizarACompra(itens)) {
                realizarACompra(itens);
                compraService.finalizarProcessamentoDaCompra(compra.getIdCompra());
            } else {
                logger.error("Nao e possivel realizar a compra");
                compraService.finalizarProcessamentoDaCompraNegada(compra.getIdCompra());
            }
        }
        catch (Exception e) {
            logger.error("Erro ao tentar realizar a compra " + compra + " " + e);
            compraService.finalizarProcessamentoDaCompraComErro(compra.getIdCompra());
        }
    }

    private boolean podeRealizarACompra(List<ItemCompra> itens) {
        boolean podeRealizarACompra = true;
        for (ItemCompra item : itens) {
            if (item.getQuantidade() > item.getProduto().getQuantidade()) {
                podeRealizarACompra = false;
                break;
            }
        }
        return !itens.isEmpty() && podeRealizarACompra;
    }

    private void realizarACompra(List<ItemCompra> itens) throws Exception {
        try {

            for(ItemCompra item : itens) {
                produtoService.subtrairEstoque(item.getProduto(), item.getQuantidade());
            }

        } catch (Exception e) {
            logger.error("erro ao atualizar o estoque");
            throw new Exception("Erro ao atualizar o estoque");
        }
    }

}
