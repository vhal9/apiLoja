package com.betaseven.lojaonline.domain.model;

import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_compra", nullable = false)
    private UUID idCompra;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Transient
    private List<ItemCompra> itensCompra;

    @Enumerated(EnumType.STRING)
    private StatusCompraEnum statusCompra;

    private Double total;

    private LocalDateTime dataCompra;

    private LocalDateTime dataInicioProcessamento;

    private LocalDateTime dataFimProcessamento;


}
