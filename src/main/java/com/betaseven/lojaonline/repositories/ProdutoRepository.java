package com.betaseven.lojaonline.repositories;

import com.betaseven.lojaonline.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Modifying
    @Query("UPDATE Produto set quantidade = :quantidade where idProduto = :idProduto")
    void atualizarEstoque(@Param("idProduto") Long idProduto, @Param("quantidade") Long novaQuantidade);

    List<Produto> findProdutoByNome(String nomeProduto);
}
