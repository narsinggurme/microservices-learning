package com.ng.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductSerivceApplicationTests
{
    /**
     * @ServiceConnection is a Spring Boot 3.1+ annotation that automatically
     * connects this Testcontainer (MongoDB in this case) to the application's
     * Spring configuration during tests.
     *
     * It removes the need to manually set properties like
     * spring.data.mongodb.uri using @DynamicPropertySource.
     */
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=port;
    }
    static {
        mongoDBContainer.start();
        System.out.println("MongoDB container started on port: " + mongoDBContainer.getFirstMappedPort());
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

	@Test
	void shouldCreateProduct() {
        String requestBody = """
                {
                    "name": "iPhone 14",
                    "description": "Latest Apple iPhone model",
                    "price": 999.99
                }
                """;
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("iPhone 14"))
                .body("description", Matchers.equalTo("Latest Apple iPhone model"))
                .body("price", Matchers.equalTo(999.99f));
	}

}
