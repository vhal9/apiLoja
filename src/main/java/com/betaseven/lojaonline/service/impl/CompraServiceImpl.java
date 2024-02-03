package com.betaseven.lojaonline.service.impl;

import com.betaseven.lojaonline.Exceptions.CompraNotFoundException;
import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import com.betaseven.lojaonline.domain.dtos.CompraDTO;
import com.betaseven.lojaonline.domain.mappers.CompraDTOMapper;
import com.betaseven.lojaonline.domain.model.Cliente;
import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.repositories.CompraRepository;
import com.betaseven.lojaonline.service.ClienteService;
import com.betaseven.lojaonline.service.CompraService;
import com.betaseven.lojaonline.service.ItemCompraService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ClienteService clienteService;
    private final ItemCompraService itemCompraService;
    private final CompraDTOMapper compraDTOMapper;
    private final Logger logger = LogManager.getLogger(CompraServiceImpl.class);


    @Override
    @Transactional
    public CompraDTO inserirCompra(CompraDTO compraDTO) throws Exception {
        try {

            Cliente cliente = clienteService.buscarCliente(compraDTO.idCliente);

            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setDataCompra(LocalDateTime.now());
            compra.setStatusCompra(StatusCompraEnum.PENDENTE);

            logger.info("Inserindo a compra: " +  compra);
            Compra compraSalva = compraRepository.save(compra);
            logger.info("Compra inserida com id: " + compraSalva.getIdCompra());

            if (!compraDTO.getItens().isEmpty()) {
                logger.info("Inserindo os itens da compra");
                itemCompraService.salvarItensCompra(compraDTO.getItens(), compraSalva);
            }
            return compraDTOMapper.execute(compraSalva);
        } catch (Exception e) {
            logger.error("Erro ao tentar salvar uma nova compra " + compraDTO, e);
            throw new RuntimeException("Erro ao tentar salvar uma nova compra " + compraDTO);
        }
    }

    @Override
    public CompraDTO buscarCompra(UUID idCompra) {
        Optional<Compra> optionalCompra = compraRepository.findById(idCompra);
        if (optionalCompra.isPresent()) {
            return compraDTOMapper.execute(optionalCompra.get());
        }
        throw new CompraNotFoundException("Compra nao encontrada para o id: " + idCompra);
    }

    @Override
    @Transactional
    public List<Compra> buscarComprasPorStatus(StatusCompraEnum statusCompraEnum) {
        return compraRepository.findAllByStatusCompraOrderByDataCompraAsc(statusCompraEnum);
    }

    @Override
    @Transactional
    public void iniciarProcessamentoDaCompra(UUID idCompra) {
        compraRepository.iniciarProcessamentoDaCompra(idCompra, StatusCompraEnum.EMPROCESSAMENTO, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void finalizarProcessamentoDaCompra(UUID idCompra) {
        logger.info("finalizarProcessamentoDaCompra - idCompra: " + idCompra);
        compraRepository.finalizarProcessamentoDaCompra(idCompra, StatusCompraEnum.REALIZADA, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void finalizarProcessamentoDaCompraNegada(UUID idCompra) {
        logger.info("finalizarProcessamentoDaCompraNegada - idCompra: " + idCompra);
        compraRepository.finalizarProcessamentoDaCompra(idCompra, StatusCompraEnum.NEGADA, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void finalizarProcessamentoDaCompraComErro(UUID idCompra) {
        logger.info("finalizarProcessamentoDaCompraComErro - idCompra: " + idCompra);
        compraRepository.finalizarProcessamentoDaCompra(idCompra, StatusCompraEnum.ERRO, LocalDateTime.now());
    }

}
