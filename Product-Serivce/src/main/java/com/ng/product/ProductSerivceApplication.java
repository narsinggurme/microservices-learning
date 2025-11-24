package com.ng.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Service API",
                version = "1.0.0",
                description = "API documentation for the Product Service"
        )
)
@SpringBootApplication
public class ProductSerivceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSerivceApplication.class, args);
    }

}
