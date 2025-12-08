package com.cep.projeto.Exceptions;

public class ViaCepException extends RuntimeException {

    public static final String VIACEP_EXCEPTION = "Erro ao buscar o %s ViaCEP pode estar fora do ar";

    public ViaCepException(String cep) {
        super(String.format(VIACEP_EXCEPTION, cep));
    }
}
