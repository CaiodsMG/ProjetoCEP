package com.cep.projeto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteDTO {

    @JsonProperty
    @Schema(example = "Caio Magalhães")
    private String nome;
    @JsonProperty
    @Schema(example = "71931000")
    private String cep;

    public ClienteDTO(String nome, String cep) {
        this.nome = nome;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }
}
