package com.cep.projeto.Controller;

import com.cep.projeto.Model.Cliente;
import com.cep.projeto.Services.ClienteService;
import com.cep.projeto.dtos.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController implements ClienteControllerApi {

    @Autowired
    private ClienteService service;


    @Override
    public List<ClienteDTO> clientes(){
        return service.listarClientes();
    }

    @Override
    public ClienteDTO cliente( Long id){
        return service.buscarPorId(id);
    }

    @Override
    public ClienteDTO inserirCliente( Cliente cliente){
        return service.inserirCliente(cliente);
    }

    @Override
    public ClienteDTO atualizarCliente(Long id, Cliente cliente){
        return service.atualizarCliente(id, cliente);
    }

    @Override
    public void deletarCliente(Long id){
        service.deletarCliente(id);
    }

}
