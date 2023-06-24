package com.wea4saken.rikmasters;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
public class RikMastersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RikMastersApplication.class, args);
    }

}
