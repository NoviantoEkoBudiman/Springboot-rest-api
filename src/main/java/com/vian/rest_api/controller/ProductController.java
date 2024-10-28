package com.vian.rest_api.controller;

import com.vian.rest_api.dto.EmployeeDto;
import com.vian.rest_api.dto.ProductDto;
import com.vian.rest_api.entity.Product;
import com.vian.rest_api.service.EmployeeService;
import com.vian.rest_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto updatedProduct){
        ProductDto update = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId){
        String result = productService.deleteProduct(productId);
        if (result.contains("tidak ditemukan")) {  // Periksa apakah hasilnya adalah pesan kesalahan
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok("Product berhasil dihapus");
    }
}