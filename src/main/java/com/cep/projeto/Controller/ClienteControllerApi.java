
package com.cep.projeto.Controller;

import com.cep.projeto.Exceptions.ErrorResponse;
import com.cep.projeto.Model.ClienteModelResponse;
import com.cep.projeto.dtos.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("clientes")
public interface ClienteControllerApi {

    @GetMapping("/listarTodos")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteModelResponse> clientes();

    @GetMapping("/buscarPor/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    public ClienteModelResponse clientePorId(@PathVariable Long id);

    @PostMapping("Cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModelResponse inserirCliente(@Valid @RequestBody ClienteDTO cliente);

    @PutMapping("/AtualizarPor/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Altera cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    public ClienteModelResponse atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente);

    @DeleteMapping("/DeletarPor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    public void deletarCliente(@PathVariable Long id);
}
