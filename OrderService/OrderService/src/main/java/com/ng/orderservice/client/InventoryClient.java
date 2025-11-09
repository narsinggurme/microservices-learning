package com.ng.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "${Inventory.url}")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    String isInStock (@RequestParam String skuCode, @RequestParam Integer quantity);
}
