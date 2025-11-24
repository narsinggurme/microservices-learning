package com.ng.inventory;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.36");

    @LocalServerPort
    private int port;

    static {
        mySQLContainer.start();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        System.out.println("🔹 Test running on port: " + port);
    }

    @Test
    void shouldReturnInStockWhenQuantityIsAvailable() {
        String response = RestAssured
                .given()
                .when()
                .get("/api/inventory?skuCode=Dell&quantity=10")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Assertions.assertEquals("IN STOCK", response.trim());
    }

    @Test
    void shouldReturnOutOfStockWhenQuantityIsNotAvailable() {
        String response = RestAssured
                .given()
                .when()
                .get("/api/inventory?skuCode=Apple&quantity=500")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Assertions.assertEquals("OUT OF STOCK", response.trim());
    }
}
