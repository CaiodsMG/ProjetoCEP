package com.cep.projeto.Exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {

    @Schema(example = "12/12/2025 18:43:19")
    private String timestamp;
    @Schema(example = "404")
    private int status;
    @Schema(example = "O usuário com id 100 não foi encontrado.")
    private String error;
    @Schema(example = "/clientes/100")
    private String path;

    public ErrorResponse(){}

    public ErrorResponse(int status, String error, String path) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
