package com.example.store.controllers;


import com.example.store.dtos.ProductRequest;
import com.example.store.models.Product;
import com.example.store.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/product") //url: http:localhost:8080/api/product
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
   //Petición Get todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //Petición GET con un ID
    @GetMapping(path = "{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }
    //Petición Post
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    //Petición Put
    @PutMapping(path = "{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable("productId") Long productId, @Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(product);
    }
    //Petición Eliminar
    @DeleteMapping(path = "{productId}")
    public ResponseEntity <Product> deleteProductById(@PathVariable("productId") Long productId) {
        Product product = productService.deleteProduct(productId);
        return ResponseEntity.ok(product);
    }
} //Cierre de clase ProductController

