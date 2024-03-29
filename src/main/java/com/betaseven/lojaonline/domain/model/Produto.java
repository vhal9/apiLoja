package com.betaseven.lojaonline.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;
    private String nome;
    private Double preco;
    private Long quantidade;

    public Produto(Long idProduto) {
        this.idProduto = idProduto;
    }
}
