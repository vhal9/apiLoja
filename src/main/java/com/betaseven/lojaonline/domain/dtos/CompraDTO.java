package com.betaseven.lojaonline.domain.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public List<ItemDTO> itens;
}
