package com.mar1a_ed.e_store.repository;

import com.mar1a_ed.e_store.model.entity.Product;

import com.mar1a_ed.e_store.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByCode(String code);

    List<Product> findByCategory(Category category);
}
