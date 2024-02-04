package com.betaseven.lojaonline.domain.dtos;


import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    private UUID idCompra;

    @NotNull(message = "Necessario informar o cliente")
    private UUID idCliente;

    @Valid
    private List<ItemDTO> itens;
    private UUID idUsuario;
    private StatusCompraEnum statusCompra;
    private Double total;
    private LocalDateTime dataCompra;
    private LocalDateTime dataInicioProcessamento;
    private LocalDateTime dataFimProcessamento;
}
