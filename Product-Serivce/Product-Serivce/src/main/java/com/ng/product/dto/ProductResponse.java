package com.ng.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String skucode, String name, String description, BigDecimal price) {

}
