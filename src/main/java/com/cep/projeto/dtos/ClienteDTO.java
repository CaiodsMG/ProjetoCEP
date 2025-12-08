package com.cep.projeto.dtos;

import com.cep.projeto.Model.Cliente;

import java.util.List;

public record ClienteDTO(
        String nome,
        EnderecoDTO endereco
){

    public static ClienteDTO paraClienteDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getNome(),
                EnderecoDTO.paraEnderecoDTO(cliente.getEndereco()));

    }

    public static List<ClienteDTO> listaClientes(List<Cliente> clientes){
        return clientes.stream().map(cliente -> paraClienteDTO(cliente)).toList();
    }
}

