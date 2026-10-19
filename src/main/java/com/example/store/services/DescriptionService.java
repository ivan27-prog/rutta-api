package com.example.store.services;

import com.example.store.dtos.DescriptionRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Description;
import com.example.store.models.Product;
import com.example.store.repository.DescriptionRepository;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository, ProductRepository productRepository) {
        this.descriptionRepository = descriptionRepository;
        this.productRepository = productRepository;
    }

    public List<Description> getAllDescriptions() {
        return descriptionRepository.findAll();
    }

    public Description getDescriptionById(Long id) {
        return descriptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Descripción con el id " + id + " no encontrada")
        );
    }

    public Description createDescription(DescriptionRequest newDescription) {
        Product product = productRepository.findById(newDescription.productId()).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + newDescription.productId() + " no encontrado")
        );

        Description description = new Description();
        description.setDescriptionText(newDescription.descriptionText());
        description.setProduct(product);
        return descriptionRepository.save(description);
    }

    public Description updateDescription(Long id, DescriptionRequest updateDescription) {
        Description oldDescription = descriptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Descripción con el id " + id + " no encontrada")
        );
        if (updateDescription.descriptionText() != null) {
            oldDescription.setDescriptionText(updateDescription.descriptionText());
        }
        if (updateDescription.productId() != null) {
            Product product = productRepository.findById(updateDescription.productId()).orElseThrow(
                    () -> new ResourceNotFoundException("Producto con el id " + updateDescription.productId() + " no encontrado")
            );
            oldDescription.setProduct(product);
        }
        return descriptionRepository.save(oldDescription);
    }

    public Description deleteDescriptionById(Long id) {
        Description description = descriptionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Descripción con el id " + id + " no encontrada")
        );
        descriptionRepository.delete(description);
        return description;
    }
}