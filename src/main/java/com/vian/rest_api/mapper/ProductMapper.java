package com.vian.rest_api.mapper;

import com.vian.rest_api.dto.ProductDto;
import com.vian.rest_api.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getNama(),
                product.getHarga()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getNama(),
                productDto.getHarga()
        );
    }
}
