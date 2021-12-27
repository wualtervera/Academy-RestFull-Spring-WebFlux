package com.nttdata.academy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Academy", version = "1.0", description = "Documentation APIs v1.0"))
@SpringBootApplication
public class AcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}

}
