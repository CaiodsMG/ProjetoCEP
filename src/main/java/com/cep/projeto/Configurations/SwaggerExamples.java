package com.cep.projeto.Configurations;

public class SwaggerExamples {

    public static final String USUARIO_CORRETO_JSON = """
            {
                "nome": "Caio Magalhaes",
                "endereco": {
                    "cep": "71931000"
                }
            }
            
            """;


    public static final String USUARIO_INCORRETO_JSON = """
            {
                "nome": "Ca",
                "endereco": {
                    "cep": "71931000"
                }
            }
            
            """;

    public static final String CEP_INCORRETO = """
             {
                "nome": "Caio Magalhaes",
                "endereco": {
                    "cep": "7193"
                }
            }
            
            """;







}
