package com.example.store.services;

import com.example.store.dtos.ProductRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Product Service es la lógica de negocio sobre la entidad de Producto
@Service
public class ProductService {
    private ProductRepository productRepository;

    // private CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Método para obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Método para obtener un producto por su ID
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id + " + id + " no encontrado"));
    }

    // Metodo para crear producto
    public Product createProduct(ProductRequest newProduct) {
        Product product = new Product();
        product.setProductName(newProduct.productName());
        product.setPrice(newProduct.price());
        product.setActivity(newProduct.activity());
        product.setLevel(newProduct.level());
        return productRepository.save(product);
    }

    // Método para actualizar product
    public Product updateProduct(long id, ProductRequest updateProduct) {
        Product oldProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));
        if (updateProduct.productName() != null)
            oldProduct.setProductName(updateProduct.productName());
        if (updateProduct.price() != null)
            oldProduct.setPrice(updateProduct.price());
        if (updateProduct.activity() != null)
            oldProduct.setActivity(updateProduct.activity());
        if (updateProduct.level() != null)
            oldProduct.setLevel(updateProduct.level());
        return productRepository.save(oldProduct);
    }

    // Metodo para eliminar producto
    public Product deleteProduct(long id) {
        // 1. Buscar el producto; si no existe, lanzar la excepción
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));

        // 2. Borrarlo de la base de datos
        productRepository.delete(product);

        // 3. Regresar el producto que se borró
        return product;
    }

}// Cierre de clase ProductService
