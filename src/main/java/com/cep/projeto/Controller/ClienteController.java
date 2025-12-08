package com.cep.projeto.Controller;

import com.cep.projeto.Services.ClienteService;
import com.cep.projeto.dtos.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> clientes(){
        return service.listarClientes();
    }
}
