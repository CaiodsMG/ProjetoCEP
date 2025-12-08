package com.cep.projeto.Repositories;

import com.cep.projeto.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
