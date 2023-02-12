package com.betaseven.lojaonline.scheduler;

import com.betaseven.lojaonline.service.ProcessamentoDeCompraService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Profile(value = "!local")
public class ProcessamentoDeCompraScheduler {

    private final ProcessamentoDeCompraService processamentoDeCompraService;
    private final Logger logger = LogManager.getLogger(ProcessamentoDeCompraScheduler.class);

    @Scheduled(cron = "${processamento.compra.pendente.cron}", zone = "${processamento.compra.pendente.zone}")
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
