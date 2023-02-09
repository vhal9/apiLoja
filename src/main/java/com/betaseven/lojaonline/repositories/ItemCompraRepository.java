package com.betaseven.lojaonline.repositories;

import com.betaseven.lojaonline.domain.model.Compra;
import com.betaseven.lojaonline.domain.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    @Query
    List<ItemCompra> findAllByCompra(Compra compra);
}
