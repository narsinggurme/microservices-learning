package com.ng.product;

import com.ng.product.dto.ProductRequest;
import com.ng.product.dto.ProductResponse;
import com.ng.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    // CREATE PRODUCT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    // GET ALL PRODUCTS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET PRODUCT BY ID (recommended)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
}
