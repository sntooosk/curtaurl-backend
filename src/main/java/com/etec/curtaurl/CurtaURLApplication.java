package com.etec.curtaurl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
public class CurtaURLApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurtaURLApplication.class, args);
	}

}
