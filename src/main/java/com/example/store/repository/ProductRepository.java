package com.example.store.repository;

import com.example.store.models.Category;
import com.example.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Obtener los productos de una categoría
    List<Product> findByCategory(Category categoryId);
}
