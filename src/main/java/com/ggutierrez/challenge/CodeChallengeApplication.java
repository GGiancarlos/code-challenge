package com.ggutierrez.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(info = @Info(
    title = "Code Challenge - Spring WebFlux Student Microservice",
    version = "1.0",
    description = "API to create and list students"
    ))
@SpringBootApplication
public class CodeChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeChallengeApplication.class, args);
    }

}
