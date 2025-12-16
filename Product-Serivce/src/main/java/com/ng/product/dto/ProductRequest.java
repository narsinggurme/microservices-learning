package com.ng.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String skuCode,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Double rating,
        Integer reviews,
        List<CommentDto> comments
) {}
