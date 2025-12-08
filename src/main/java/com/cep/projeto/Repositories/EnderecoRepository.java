package com.cep.projeto.Repositories;

import com.cep.projeto.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
