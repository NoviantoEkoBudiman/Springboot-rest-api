package com.vian.rest_api.service.implement;

import com.vian.rest_api.dto.ProductDto;
import com.vian.rest_api.entity.Product;
import com.vian.rest_api.exception.ResourceNotFoundException;
import com.vian.rest_api.mapper.ProductMapper;
import com.vian.rest_api.repository.ProductRepository;
import com.vian.rest_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto){
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()->
            new ResourceNotFoundException("Data not founcd")
        );
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map((product)-> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct){
        Product product = productRepository.findById(productId).orElseThrow(()->
            new ResourceNotFoundException("Data not found")
        );
        product.setNama(updatedProduct.getNama());
        product.setHarga(updatedProduct.getHarga());
        Product update = productRepository.save(product);
        return ProductMapper.mapToProductDto(update);
    }

    @Override
    public String deleteProduct(Long productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    productRepository.delete(product);
                    return "Product dengan ID " + productId + " berhasil dihapus.";
                })
                .orElse("Product dengan ID " + productId + " tidak ditemukan.");
    }
}
