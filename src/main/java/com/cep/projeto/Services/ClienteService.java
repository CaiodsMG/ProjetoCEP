package com.cep.projeto.Services;

import com.cep.projeto.Exceptions.UsuarioNaoEncontrado;
import com.cep.projeto.Exceptions.ViaCepException;
import com.cep.projeto.Model.Cliente;
import com.cep.projeto.Model.Endereco;
import com.cep.projeto.Repositories.ClienteRepository;
import com.cep.projeto.Repositories.EnderecoRepository;
import com.cep.projeto.dtos.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    public List<ClienteDTO> listarClientes() {
        return ClienteDTO.listaClientes(repository.findAll());
    }


    public ClienteDTO buscarPorId(Long id){
        Cliente cliente = repository.findById(id).orElseThrow(() ->
                new UsuarioNaoEncontrado(id));

        return ClienteDTO.paraClienteDTO(cliente);
    }

    public ClienteDTO inserirCliente(Cliente cliente){
        return salvarClienteComCep(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteId = repository.findById(id);
        if (clienteId.isPresent()) {
            salvarClienteComCep(cliente);
        }
        throw new UsuarioNaoEncontrado(id);
    }

    public void deletarCliente(Long id){
        repository.deleteById(id);
    }


    private ClienteDTO salvarClienteComCep(Cliente cliente) {

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        cliente.setEndereco(endereco);

        Cliente salvo = repository.save(cliente);

        return ClienteDTO.paraClienteDTO(salvo);
    }

    private Endereco consultarCep(String cep){
        try{
            return viaCepService.consultarCep(cep);
        } catch (Exception e) {
            throw new ViaCepException(cep);
        }

    }

}
