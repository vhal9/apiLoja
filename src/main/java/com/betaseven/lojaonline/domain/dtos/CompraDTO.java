package com.betaseven.lojaonline.domain.dtos;


import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    public Long idCompra;

    @NotNull
    public Long idCliente;

    @NotEmpty
    @Valid
    public List<ItemDTO> itens;

    public StatusCompraEnum statusCompra;

    public Double total;

    public LocalDateTime dataCompra;

    public LocalDateTime dataProcessamento;
}
