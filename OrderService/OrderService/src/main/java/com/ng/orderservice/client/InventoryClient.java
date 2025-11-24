package com.ng.orderservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.logging.Logger;


public interface InventoryClient {
    Logger log = Logger.getLogger(InventoryClient.class.getName());

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    @Retry(name = "inventory")
    String isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default String inventoryFallback(String skuCode, Integer quantity, Throwable t) {
        log.warning(
                "Fallback triggered for SKU: " + skuCode + ", qty: " + quantity + " due to: " + t.getMessage()
        );
        return "false";
    }
}
