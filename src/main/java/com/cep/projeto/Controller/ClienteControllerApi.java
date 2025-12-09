package com.cep.projeto.Controller;

import com.cep.projeto.Model.Cliente;
import com.cep.projeto.dtos.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("clientes")
public interface ClienteControllerApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> clientes();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO cliente(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO inserirCliente(@Valid @RequestBody Cliente cliente);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClienteDTO atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id);
}
