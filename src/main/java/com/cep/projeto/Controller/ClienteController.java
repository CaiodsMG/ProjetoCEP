
package com.cep.projeto.Controller;

import com.cep.projeto.Entities.Cliente;
import com.cep.projeto.Model.ClienteModelResponse;
import com.cep.projeto.Services.ClienteService;
import com.cep.projeto.dtos.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController implements ClienteControllerApi {

    @Autowired
    private ClienteService service;


    @Override
    public List<ClienteModelResponse> clientes(){
        return service.listarClientes();
    }

    @Override
    public ClienteModelResponse clientePorId(Long id){
        return service.buscarPorId(id);
    }

    @Override
    public ClienteModelResponse inserirCliente( ClienteDTO cliente){
        return service.inserirCliente(cliente);
    }

    @Override
    public ClienteModelResponse atualizarCliente(Long id, ClienteDTO cliente){
        return service.atualizarCliente(id, cliente.getNome(), cliente.getCep());
    }

    @Override
    public void deletarCliente(Long id){
        service.deletarCliente(id);
    }

}
