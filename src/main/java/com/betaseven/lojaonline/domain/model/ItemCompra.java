package com.betaseven.lojaonline.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_item_compra", nullable = false)
    private Long idItemCompra;
    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    private Long quantidade;
}
