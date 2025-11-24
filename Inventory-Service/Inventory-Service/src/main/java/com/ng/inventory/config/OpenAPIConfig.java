package com.ng.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI invertoryOpenAPI() {
        return new OpenAPI( )
                .info(new Info()
                        .title("Inventory Management API")
                        .description("API documentation for the Inventory Service")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("Inventory Service Wiki Documentation")
                        .url("https://localhost:dummyurl.com/api/docs"));
    }
}
