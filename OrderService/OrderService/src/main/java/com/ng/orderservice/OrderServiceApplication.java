package com.ng.orderservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
@OpenAPIDefinition is used to provide metadata for the OpenAPI documentation.
It includes information such as the title, version, description, and license of the API.
and we used only this annotation instead of creating a separate configuration class when project is small and simple.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Order Service API",
                version = "1.0.0",
                description = "API documentation for the Order Service",
                license = @License(name = "Apache 5.0", url = "http://springdoc.org")
        )
)


@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
