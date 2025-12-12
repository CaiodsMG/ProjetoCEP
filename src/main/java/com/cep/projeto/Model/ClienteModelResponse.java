package com.cep.projeto.Model;

import com.cep.projeto.Entities.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ClienteModelResponse {

    @JsonProperty("id")
    @Schema(example = "1")
    private Long id;


    @JsonProperty("nome")
    @Schema(example = "Caio Magalhães")
    private String nome;

    @JsonProperty("Endereço")
    @Schema(description = "Endereço do cliente")
    private EnderecoModelResponse endereco;

    public ClienteModelResponse(Long id, String nome, EnderecoModelResponse endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public static ClienteModelResponse paraClienteResponse(Cliente cliente){
        return new ClienteModelResponse(
                cliente.getId()
                ,cliente.getNome(),
                EnderecoModelResponse.paraEnderecoResponse(cliente.getEndereco()));

    }

    public static List<ClienteModelResponse> listaClientes(List<Cliente> clientes){
        return clientes.stream().map(cliente -> paraClienteResponse(cliente)).toList();
    }

}
