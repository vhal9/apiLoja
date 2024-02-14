package com.betaseven.lojaonline.repositories;

import com.betaseven.lojaonline.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findClienteByCnpjOrRazaoSocial(String cnpj, String razaoSocial);

    @Query("select c from Cliente c where lower(c.nomeFantasia) like lower(CONCAT('%',:nomeFantasia,'%'))")
    List<Cliente> findClienteByNomeFantasia(@Param("nomeFantasia") String nomeFantasia);
}
