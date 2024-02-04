package com.betaseven.lojaonline.repositories;

import com.betaseven.lojaonline.domain.Enum.StatusCompraEnum;
import com.betaseven.lojaonline.domain.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CompraRepository extends JpaRepository<Compra, UUID> {
    @Query
    List<Compra> findAllByStatusCompraOrderByDataCompraAsc(StatusCompraEnum status);

    @Modifying
    @Query(value = "UPDATE Compra SET statusCompra = :status, dataInicioProcessamento = :data where idCompra = :id")
    void iniciarProcessamentoDaCompra(@Param("id") UUID idCompra, @Param("status") StatusCompraEnum status, @Param("data") LocalDateTime data);

    @Modifying
    @Query(value = "UPDATE Compra SET statusCompra = :status, dataFimProcessamento = :data where idCompra = :id")
    void finalizarProcessamentoDaCompra(@Param("id") UUID idCompra, @Param("status") StatusCompraEnum status, @Param("data") LocalDateTime data);


}
