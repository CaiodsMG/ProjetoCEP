package com.cep.projeto.Repositories;

import com.cep.projeto.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
