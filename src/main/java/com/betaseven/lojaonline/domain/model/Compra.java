package com.betaseven.lojaonline.domain.model;

import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_compra", nullable = false)
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Transient
    private List<ItemCompra> itensCompra;

    private StatusCompraEnum statusCompra;

    private Double total;

    private LocalDateTime dataCompra;

    private LocalDateTime dataProcessamento;


}
