package com.cep.projeto.Services;

import com.cep.projeto.Exceptions.UsuarioNaoEncontrado;
import com.cep.projeto.Model.Cliente;
import com.cep.projeto.Model.Endereco;
import com.cep.projeto.Repositories.ClienteRepository;
import com.cep.projeto.Repositories.EnderecoRepository;
import com.cep.projeto.dtos.ClienteDTO;
import com.cep.projeto.dtos.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<ClienteDTO> lista = new ArrayList<>();

        for (Cliente c : repository.findAll()){
            lista.add(transformaCliente(c));
        }

        return lista;
    }


    public Cliente buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() ->
                new UsuarioNaoEncontrado("O usuário com id " + id + " não foi encontrado." ));
    }

    public ClienteDTO inserirCliente(Cliente cliente){
        return salvarClienteComCep(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteId = repository.findById(id);
        if (clienteId.isPresent()) {
            salvarClienteComCep(cliente);
        }
        throw new UsuarioNaoEncontrado("O usuário com id " + id + " não foi encontrado." );
    }

    public void deletarCliente(Long id){
        repository.deleteById(id);
    }


    private ClienteDTO salvarClienteComCep(Cliente cliente) {

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        cliente.setEndereco(endereco);

        Cliente salvo = repository.save(cliente);

        return transformaCliente(salvo);
    }



    private EnderecoDTO transformaEndereco(Endereco endereco){
        return new EnderecoDTO(
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf(),
                endereco.getDdd());
    }

    private ClienteDTO transformaCliente(Cliente cliente){
        return new ClienteDTO(
                cliente.getNome(),
                transformaEndereco(cliente.getEndereco()));
    }

}
