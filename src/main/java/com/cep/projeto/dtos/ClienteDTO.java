package com.cep.projeto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {

    @JsonProperty
    @Schema(example = "Caio Magalhães")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @JsonProperty
    @Schema(example = "71931000")
    @NotBlank(message = "O CEP não pode estar em branco.")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos e apenas números")
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
