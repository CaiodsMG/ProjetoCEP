package com.cep.projeto.dtos;

public record EnderecoDTO(
        String cep,
        String bairro,
        String localidade,
        String uf,
        String ddd
) {}

