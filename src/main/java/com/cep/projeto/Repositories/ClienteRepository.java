package com.cep.projeto.Repositories;

import com.cep.projeto.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(
            "SELECT c FROM Cliente c " +
                    "WHERE UPPER(c.nome) " +
                    "LIKE UPPER(CONCAT('%', :nome, '%'))"
    )
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    @Query(
            "SELECT c FROM Cliente c " +
                    "WHERE UPPER(c.endereco.uf) " +
                    "LIKE UPPER(CONCAT('%', :uf, '%'))"
    )
    List<Cliente> buscarPorUf(@Param("uf") String uf);


    @Query("SELECT c FROM Cliente c " +
            "WHERE UPPER(c.endereco.localidade) " +
            "LIKE UPPER(CONCAT('%', :localidade, '%'))"
    )
    List<Cliente> buscarPorCidade(@Param("localidade") String localidade);


}