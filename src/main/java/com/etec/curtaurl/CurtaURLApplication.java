package com.etec.curtaurl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Curta URL", version = "1", description = "API desenvolvida para o backend do Curta URL"))
@EnableScheduling
public class CurtaURLApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtaURLApplication.class, args);
	}

}
