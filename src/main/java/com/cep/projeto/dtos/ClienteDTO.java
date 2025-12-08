package com.cep.projeto.dtos;

import com.cep.projeto.Model.Cliente;

public record ClienteDTO(
        String nome,
        EnderecoDTO endereco
){}

