package com.ng.orderservice.dto;
public record OrderRequest(
        String orderNumber,
        String skuCode,
        int quantity,
        double price,
        UserDetails userDetails
) {
    public record UserDetails(String firstName, String lastName, String email) {}
}
