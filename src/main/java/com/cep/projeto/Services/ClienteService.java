package com.cep.projeto.Services;

import com.cep.projeto.Exceptions.UsuarioNaoEncontrado;
import com.cep.projeto.Exceptions.ViaCepException;
import com.cep.projeto.Entities.Cliente;
import com.cep.projeto.Entities.Endereco;
import com.cep.projeto.Model.ClienteModelResponse;
import com.cep.projeto.Repositories.ClienteRepository;
import com.cep.projeto.Repositories.EnderecoRepository;
import com.cep.projeto.dtos.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    public List<ClienteModelResponse> listarClientes() {
        return ClienteModelResponse.listaClientes(repository.findAll());
    }


    public ClienteModelResponse buscarPorId(Long id){
        Cliente cliente = repository.findById(id).orElseThrow(() ->
                new UsuarioNaoEncontrado(id));

        return ClienteModelResponse.paraClienteResponse(cliente);
    }

    public ClienteModelResponse inserirCliente(ClienteDTO cliente){
        return salvarClienteComCep(cliente);
    }

    public ClienteModelResponse atualizarCliente(Long id, String nome, String cep){
        return atualizarClienteComCep(id, nome, cep);
    }


    public void deletarCliente(Long id){
        repository.deleteById(id);
    }


    private ClienteModelResponse salvarClienteComCep(ClienteDTO cliente) {

        Cliente novoCliente = new Cliente();

        String cep = cliente.getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        novoCliente.setEndereco(endereco);
        novoCliente.setNome(cliente.getNome());

        Cliente salvo = repository.save(novoCliente);

        return ClienteModelResponse.paraClienteResponse(salvo);
    }

    public ClienteModelResponse atualizarClienteComCep(Long id, String nome, String cep) {
        Cliente clienteEncontrado = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontrado(id));

        clienteEncontrado.setNome(nome);

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        clienteEncontrado.setEndereco(endereco);

        Cliente salvo = repository.save(clienteEncontrado);

        return ClienteModelResponse.paraClienteResponse(salvo);
    }


    private Endereco consultarCep(String cep){
        try{
            return viaCepService.consultarCep(cep);
        } catch (Exception e) {
            throw new ViaCepException(cep);
        }

    }

}
