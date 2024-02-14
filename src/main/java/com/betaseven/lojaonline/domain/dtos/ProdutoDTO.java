package com.betaseven.lojaonline.domain.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoDTO {

    private Long idProduto;

    @NotEmpty(message = "nome do produto nao pode ser vazio")
    private String nome;

    @NotNull(message = "preco do produo nao pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preco do produto deve ser maior que 0")
    private Double preco;

    @NotNull(message = "A quantidade do produto nao pode ser nula")
    @Min(value = 0, message = "A quantidade do produto deve ser maior ou igual a 0")
    private Long quantidade;
}
