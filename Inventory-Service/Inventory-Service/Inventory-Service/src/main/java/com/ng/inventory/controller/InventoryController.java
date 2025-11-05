package com.ng.inventory.controller;

import com.ng.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String isInStock(String skuCode, Integer quantity) {
        boolean inStock = inventoryService.isInStock(skuCode, quantity);
        return inStock ? "IN STOCK" : "OUT OF STOCK";
    }
}
