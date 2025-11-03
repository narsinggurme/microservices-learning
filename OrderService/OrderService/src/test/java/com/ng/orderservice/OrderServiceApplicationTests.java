package com.ng.orderservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @SuppressWarnings("unused") // Managed automatically by Spring Boot Testcontainers
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.36");

    @SuppressWarnings("unused") // Value injected at runtime
    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        System.out.println("Running tests on port: " + port);
    }

    @Test
    void shouldPlaceOrder() {
        String submitOrderRequest = """
                {
                   "skuCode":"Iphone",
                   "orderNumber" : 1,
                   "price":1234,
                   "quantity" : 1
                }
                """;

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(submitOrderRequest)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201);
    }
}
