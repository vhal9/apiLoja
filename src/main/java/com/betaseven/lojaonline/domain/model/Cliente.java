package com.betaseven.lojaonline.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente", nullable = false)
    private UUID idCliente;
    private String nomeFantasia;
    @Column(unique = true)
    private String razaoSocial;
    @Column(unique = true)
    private String cnpj;
    private String email;
    private String telefone;
    private LocalDate dataCriacao;
    private Boolean flAtivo;
}
