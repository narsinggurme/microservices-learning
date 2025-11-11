package com.ng.orderservice.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
    @GetExchange("/api/inventory")
    String isInStock (@RequestParam String skuCode, @RequestParam Integer quantity);
}
