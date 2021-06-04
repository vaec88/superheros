package com.superheros;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
@EnableCaching
public class SuperherosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperherosApplication.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.security(Arrays.asList(new SecurityRequirement().addList("bearerAuth")))
				.info(new Info().title("API Documentation").description("Superheros REST Services"))
				.addServersItem(new Server().url("http://localhost:8080").description("Localhost"));
	}

}

