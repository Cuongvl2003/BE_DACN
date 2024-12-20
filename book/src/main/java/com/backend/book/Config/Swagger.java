package com.backend.book.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    private String appVersion = "v1.1.1";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("API Documentation").version(appVersion)
                .description("API documentation for my Spring Boot project"));
    }

}