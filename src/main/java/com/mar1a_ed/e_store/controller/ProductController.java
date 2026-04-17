package com.mar1a_ed.e_store.controller;

import com.mar1a_ed.e_store.dto.product.ProductCreateDto;
import com.mar1a_ed.e_store.dto.product.ProductMapper;
import com.mar1a_ed.e_store.dto.product.ProductResponseDto;
import com.mar1a_ed.e_store.model.entity.Product;
import com.mar1a_ed.e_store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> register(@Valid @RequestBody ProductCreateDto productCreateDto){
        Product product = ProductMapper.toProduct(productCreateDto);
        productService.register(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(product));
    }

    @GetMapping("/code={code}")
    public ResponseEntity<ProductResponseDto> findByCode(@PathVariable String code){
        Product product = productService.findByCode(code);
        ProductResponseDto productDto = ProductMapper.toDto(product);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/category={category}")
    public ResponseEntity<List<ProductResponseDto>> findByCategory(@PathVariable String category){
        List<Product> products = productService.findByCategory(category);
        List<ProductResponseDto> productsDto = ProductMapper.toListDto(products);
        return ResponseEntity.ok(productsDto);
    }

    @PatchMapping("/update-stock/code={code}/unavailable")
    public ResponseEntity<Void> updateStockZero(@PathVariable String code){
        productService.updateStockZero(code);
        return ResponseEntity.noContent().build();
    }
}
