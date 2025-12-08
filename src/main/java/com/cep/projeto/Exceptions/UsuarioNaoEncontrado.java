package com.cep.projeto.Exceptions;

public class UsuarioNaoEncontrado extends RuntimeException {

    public static final String USUARIO_NAOENCONTRADO_EXCEPTION = "O usuário com id %d não foi encontrado.";

    public UsuarioNaoEncontrado(Long id) {
        super(String.format(USUARIO_NAOENCONTRADO_EXCEPTION, id));
    }
}
