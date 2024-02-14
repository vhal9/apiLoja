package com.betaseven.lojaonline.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    protected UUID idCliente;

    @NotEmpty(message = "Campo nome fantasia nao pode ser vazio")
    protected String nomeFantasia;
    @NotEmpty(message = "Campo nome fantasia nao pode ser vazio")
    protected String razaoSocial;

    @NotEmpty(message = "Campo CNPJ nao pode ser vazio")
    @Size(min = 14, max = 14, message = "Campo CNPJ deve ter 14 caracteres")
    protected String cnpj;

    @NotEmpty(message = "Campo email nao pode ser vazio")
    protected String email;

    @NotEmpty(message = "Campo telefone nao pode ser vazio")
    @Size(min = 10, max = 14, message = "Campo telefone deve ter entre 10 e 14 caracteres")
    protected String telefone;
    protected LocalDate dataCriacao;
    protected Boolean flAtivo;
}
