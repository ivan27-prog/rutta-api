package com.example.store.services;

import com.example.store.dtos.CategoryRequest;
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
    private CategoryRepository categoryRepository;

    //Obtener todas las categorias
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Obtener una categoría por ID
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con el id " + categoryId + " no encontrada")
                );
    }
    //Metodo para crear una categoria
    public Category createCategory(CategoryRequest categoryRequest) {
            Category category = new Category();
            category.setCategoryName(categoryRequest.categoryName());
            categoryRepository.save(category);
            return category;
    }
    //Metodo para actualizar una categoria
    public Category updateCategoryById(Long categoryId, CategoryRequest categoryRequest) {
        //Busqueda de categoría existente mediante su id
        Category category = getCategoryById(categoryId);

        if (categoryRequest.categoryName() != null)
            category.setCategoryName(categoryRequest.categoryName());

        return categoryRepository.save(category);
    }


    //  Metodo para eliminar una categoría
    public Category deleteCategoryById(Long categoryId) {
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
        return category;
    }
}
