package com.cep.projeto.dtos;

import com.cep.projeto.Entities.Endereco;

public record EnderecoDTO(
        String cep,
        String bairro,
        String localidade,
        String uf,
        String ddd
) {


}

