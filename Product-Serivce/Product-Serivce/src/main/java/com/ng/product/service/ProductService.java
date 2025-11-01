package com.ng.product.service;

import com.ng.product.dto.ProductRequest;
import com.ng.product.dto.ProductResponse;
import com.ng.product.model.Product;
import com.ng.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.name())  // instead of productRequest.getName(), because it's a record
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts()
    {
        return productRepository.findAll()
                .stream()
                .map(Product -> new ProductResponse(
                        Product.getId(),
                        Product.getName(),
                        Product.getDescription(),
                        Product.getPrice()
                )).toList();
    }
}
