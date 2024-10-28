package com.vian.rest_api.service;

import com.vian.rest_api.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(Long productId);
    List<ProductDto> getProducts();
    ProductDto updateProduct(Long productId, ProductDto updatedProduct);
    String deleteProduct(Long productId);
}
