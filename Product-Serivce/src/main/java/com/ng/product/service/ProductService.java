package com.ng.product.service;

import com.ng.product.dto.CommentDto;
import com.ng.product.dto.ProductRequest;
import com.ng.product.dto.ProductResponse;
import com.ng.product.model.Comment;
import com.ng.product.model.Product;
import com.ng.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .skuCode(productRequest.skuCode())
                .category(productRequest.category())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .imageUrl(productRequest.imageUrl())
                .rating(productRequest.rating())
                .reviews(productRequest.reviews())
                .comments(mapToComments(productRequest.comments()))
                .build();

        productRepository.save(product);

        log.info("Product {} is saved", product.getId());

        return mapToResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public  List<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategory(),
                product.getSkuCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getRating(),
                product.getReviews(),
                mapToCommentDtos(product.getComments())
        );
    }

    private List<Comment> mapToComments(List<CommentDto> commentDtos) {
        if (commentDtos == null) return List.of();

        return commentDtos.stream()
                .map(dto -> Comment.builder()
                        .user(dto.user())
                        .stars(dto.stars())
                        .comment(dto.comment())
                        .date(dto.date())
                        .build())
                .toList();
    }

    private List<CommentDto> mapToCommentDtos(List<Comment> comments) {
        if (comments == null) return List.of();

        return comments.stream()
                .map(c -> new CommentDto(
                        c.getUser(),
                        c.getStars(),
                        c.getComment(),
                        c.getDate()
                ))
                .toList();
    }
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponse(product);
    }

}
