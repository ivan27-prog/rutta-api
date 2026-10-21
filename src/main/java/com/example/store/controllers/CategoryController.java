package com.example.store.controllers;


import com.example.store.dtos.CategoryRequest;
import com.example.store.models.Category;
import com.example.store.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (path = "/api") //local
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;

    //Peticion GET obtener todas las categorías
    @GetMapping(path = "categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //Petición GET obtener una categoría por su ID
    @GetMapping(path = "categories/{categoryId}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable("categoryId") Long categoryId) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(categoryId)
        );
    }

    //Peticion POST crear una categoria
    @PostMapping(path = "categories")
    public ResponseEntity<Category> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest);

        // Construir la URL del recurso creado
        // Ejemplo: http://localhost:8080/api/categories/5
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        // Devolver HTTP 201 Created con la categoría creada
        return ResponseEntity.created(location).body(category);
    }


    //Peticion PUT Actualizar una categoría por su ID
    @PutMapping(path = "categories/{categoryId}")
    public ResponseEntity<Category> updateCategoryById(
            @PathVariable("categoryId") Long categoryId,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        Category category = categoryService.updateCategoryById(
                categoryId, categoryRequest
        );
        return ResponseEntity.ok(category);
    }

    //Peticion DELETE eliminar una categoría por su ID
    @DeleteMapping(path = "categories/{categoryId}")
    public ResponseEntity<Category> deleteCategoryById(
            @PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
}
