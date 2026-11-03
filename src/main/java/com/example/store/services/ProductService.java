package com.example.store.services;

import com.example.store.models.Category;
import com.example.store.repository.CategoryRepository;
import com.example.store.dtos.ProductRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.store.models.Color;
import com.example.store.models.Size;
import com.example.store.repository.ColorRepository;
import com.example.store.repository.SizeRepository;
import java.util.HashSet;
import java.util.Set;

//Product Service es la lógica de negocio sobre la entidad de Producto
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ColorRepository colorRepository,
            SizeRepository sizeRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
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

    private void asignarColoresYTallas(Product product, ProductRequest request) {

        if (request.colorIds() != null) {
            Set<Color> colores = new HashSet<>(
                    colorRepository.findAllById(request.colorIds())
            );

            if (colores.size() != new HashSet<>(request.colorIds()).size()) {
                throw new ResourceNotFoundException("Uno o más colores no existen");
            }
            product.setColors(colores);
        }

        if (request.sizeIds() != null) {
            Set<Size> tallas = new HashSet<>(
                    sizeRepository.findAllById(request.sizeIds())
            );

            if (tallas.size() != new HashSet<>(request.sizeIds()).size()) {
                throw new ResourceNotFoundException("Una o más tallas no existen");
            }
            product.setSizes(tallas);
        }
    }

    // Metodo para crear producto
    public Product createProduct(ProductRequest newProduct) {

        Product product = new Product();

        product.setProductName(newProduct.productName());
        product.setPrice(newProduct.price());
        product.setActivity(newProduct.activity());
        product.setLevel(newProduct.level());

        // Buscamos la categoría seleccionada por su ID
        Category category = categoryRepository
                .findById(newProduct.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría con el id " + newProduct.categoryId() + " no encontrada"
                ));

        // Relacionamos la categoría con el producto
        product.setCategory(category);

        asignarColoresYTallas(product, newProduct);

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
        if (updateProduct.categoryId() != null) {
            Category category = categoryRepository
                    .findById(updateProduct.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categoría no encontrada"
                    ));
            oldProduct.setCategory(category);
        }
        asignarColoresYTallas(oldProduct, updateProduct);
        return productRepository.save(oldProduct);
    }

    // Metodo para eliminar producto
    public Product deleteProduct(long id) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));

        product.getColors().size();
        product.getSizes().size();
        if (product.getDescription() != null) {
            product.getDescription().getDescriptionText();
        }

        productRepository.delete(product);

        return product;
    }

}// Cierre de clase ProductService
