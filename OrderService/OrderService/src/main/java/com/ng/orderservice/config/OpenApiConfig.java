package com.ng.orderservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
    This configuration class sets up the OpenAPI documentation for the Order Service.
    It provides metadata such as title, description, version, license, and external documentation link.
    We use this approach when the project is larger and requires more detailed configuration.
    And to keep our main class clean and focused on application startup and also code is readable.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("Order Service API")
                        .description("API documentation for the Order Service")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("Order Service Wiki Documentation")
                        .url("https://dummyurl.com/docs/orderservice"));
    }

}
