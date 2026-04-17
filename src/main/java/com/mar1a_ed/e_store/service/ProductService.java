package com.mar1a_ed.e_store.service;

import com.mar1a_ed.e_store.exception.NoProductsRegisterException;
import com.mar1a_ed.e_store.exception.ProductNotFoundException;
import com.mar1a_ed.e_store.model.entity.Product;
import com.mar1a_ed.e_store.model.enums.Category;
import com.mar1a_ed.e_store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product register(Product product){
        try{
            if(product.getCategory() == null){
                product.setCategory(Category.UNKNOWN);
            }

            return productRepository.save(product);
        }catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Product findByCode(String code){
        try{
            Product product = productRepository.findByCode(code);
            return product;
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Product {code=%s} not found", code));
        }
    }

    public List<Product> findByCategory(String category){

        Category categoryEnum = Enum.valueOf(Category.class, category);
        List<Product> products = productRepository.findByCategory(categoryEnum);

        if(products.isEmpty()){
            throw new NoProductsRegisterException(String.format("No products register for category %s",category));
        }

        return products;

    }

    public void updateStockZero(String code){
        Product product = findByCode(code);

        if(product.getStockQuantity() == 0) {
            throw new RuntimeException("The product already have 'stockQuantity' equals to 0 (unavailable)");
        }

        product.setStockQuantity(0);
        productRepository.save(product);
    }
}
