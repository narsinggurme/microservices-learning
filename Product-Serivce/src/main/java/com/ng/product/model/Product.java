package com.ng.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String id;
    private String skuCode;
    private String category;
    private String name;
    private String description;
    private BigDecimal price;

    private String imageUrl;

    private Double rating;
    private Integer reviews;

    private List<Comment> comments;
}
