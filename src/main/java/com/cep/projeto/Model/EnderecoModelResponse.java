package com.cep.projeto.Model;

import com.cep.projeto.Entities.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoModelResponse {


    @JsonProperty("cep")
    @Schema(example = "71931000")
    String cep;

    @JsonProperty("bairro")
    @Schema(example = "Aguas Claras")
    String bairro;

    @JsonProperty("localidade")
    @Schema(example = "Brasilia")
    String localidade;

    @JsonProperty("uf")
    @Schema(example = "DF")
    String uf;

    @JsonProperty("ddd")
    @Schema(example = "61")
    String ddd;

    public EnderecoModelResponse(String cep, String bairro, String localidade, String uf, String ddd) {
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ddd = ddd;
    }

    public static EnderecoModelResponse paraEnderecoResponse(Endereco endereco){
        return new EnderecoModelResponse(
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf(),
                endereco.getDdd());

    }
}
