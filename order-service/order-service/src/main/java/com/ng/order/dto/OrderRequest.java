package com.ng.order.dto;

public record OrderRequest( long id, String orderNumber, String skuCode, int quantity, double price ) {
}
