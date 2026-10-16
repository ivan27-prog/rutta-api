package com.example.store.services;

import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Category;
import com.example.store.repository.CategoryRepository;
import com.example.store.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public List<Category> findByProductId(Long productId) {
        if (!productRepository.existsById(productId)) throw new ResourceNotFoundException(
                "Producto con id " + productId + " no encontrado"
        );
        return categoryRepository.findByProductId(productId);
    }


}
