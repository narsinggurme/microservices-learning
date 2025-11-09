package com.ng.inventory.repository;

import com.ng.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityGreaterThan(String skuCode, Integer quantity);
}
