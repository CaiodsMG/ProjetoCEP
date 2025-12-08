package com.cep.projeto.dtos;

import com.cep.projeto.Model.Endereco;

public record EnderecoDTO(
        String cep,
        String bairro,
        String localidade,
        String uf,
        String ddd
) {

    public static EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return new EnderecoDTO(
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf(),
                endereco.getDdd());

    }
}

