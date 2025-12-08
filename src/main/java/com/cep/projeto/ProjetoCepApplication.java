package com.cep.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjetoCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCepApplication.class, args);
	}

}
