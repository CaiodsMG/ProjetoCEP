package com.cep.projeto.Services;

import com.cep.projeto.Exceptions.UsuarioNaoEncontrado;
import com.cep.projeto.Exceptions.ViaCepException;
import com.cep.projeto.Entities.Cliente;
import com.cep.projeto.Entities.Endereco;
import com.cep.projeto.Model.ClienteModelResponse;
import com.cep.projeto.Repositories.ClienteRepository;
import com.cep.projeto.Repositories.EnderecoRepository;
import com.cep.projeto.dtos.ClienteDTO;
import org.jspecify.annotations.NonNull;
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

    public List<ClienteModelResponse> buscarPorParteNome(String nome){
        List<Cliente> clientes = repository.buscarPorNome(nome);

        return getClientes(clientes);
    }
    
    public List<ClienteModelResponse> buscarPorUf(String uf){
        List<Cliente> clientes = repository.buscarPorUf(uf);
        
        return getClientes(clientes);
    }

    public List<ClienteModelResponse> buscarPorCidade(String localidade){
        List<Cliente> clientes = repository.buscarPorCidade(localidade);

        return getClientes(clientes);
    }

    public List<ClienteModelResponse> buscarPorNomeEUf(String nome, String uf){
        List<Cliente> clientes = repository.buscarPorNomeEUf(nome, uf);
        return getClientes(clientes);
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
            Endereco endereco = viaCepService.consultarCep(cep);

            if (endereco == null || endereco.getCep() == null || endereco.getCep().isBlank()) {
                throw new ViaCepException(cep);
            }

            return endereco;
        } catch (Exception e) {
            if (e instanceof ViaCepException viaCepException) {
                throw viaCepException;
            }
            throw new ViaCepException(cep);
        }

    }

    private static @NonNull List<ClienteModelResponse> getClientes(List<Cliente> clientes) {
        return ClienteModelResponse.listaClientes(clientes);
    }
}
