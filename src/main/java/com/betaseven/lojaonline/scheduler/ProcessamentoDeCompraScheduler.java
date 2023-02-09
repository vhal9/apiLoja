package com.betaseven.lojaonline.scheduler;

import com.betaseven.lojaonline.service.ProcessamentoDeCompraService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ProcessamentoDeCompraScheduler {

    private final ProcessamentoDeCompraService processamentoDeCompraService;
    private final Logger logger = LogManager.getLogger(ProcessamentoDeCompraScheduler.class);

    @Scheduled(cron = "${cron.processamento.compra.pendente}", zone = "America/Sao_Paulo")
    public void ProcessamentoDeComprasPendentes() {
        try {
            logger.info("Job ProcessamentoDeComprasPendentes - iniciado");
            processamentoDeCompraService.process();
            logger.info("Job ProcessamentoDeComprasPendentes - encerrado");
        }
        catch (Exception e) {
            logger.error("ProcessamentoDeComprasPendentes", e);
        }
    }
}
